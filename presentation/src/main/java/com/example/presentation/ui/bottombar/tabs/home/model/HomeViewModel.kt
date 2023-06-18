package com.example.presentation.ui.bottombar.tabs.home.model

import android.util.Log
import androidx.compose.material.TabPosition
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.Dispatchers
import com.example.domain.api.CardInteractor
import com.example.domain.api.UserInteractor
import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import com.example.entity.UserEntity
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BaseBankTransaction
import com.example.presentation.ui.bottombar.tabs.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    private val cardInteractor: CardInteractor,
    private val dispatchers: Dispatchers,
) : StatefulScreenModel<HomeState, SideEffect>(HomeState()) {

    init {
        subscribeToUser()
        subscribeToBankCards()
    }

    private fun subscribeToUser() {
        viewModelScope.launch {
            userInteractor
                .userFlow
                .filterNotNull()
                .distinctUntilChanged { old, new -> old.id == new.id }
                .onEach { user ->
                    reduceState { getState().copy(userValue = user, isUserLoadingValue = false) }
                }
                .collect()
        }
    }

    private fun subscribeToBankCards() {
        viewModelScope.launch(dispatchers.io) {
            cardInteractor
                .cardsFlow
                .distinctUntilChanged()
                .onEach { cards ->
                    if (cards.isNullOrEmpty()) cardInteractor.fetchCards(getState().getUserId())
                }
                .filterNotNull()
                .map { cards ->
                    cards.map { BankCard(it) }
                }
                .onEach { cards ->
                    reduceState { getState().copy(cardsValue = cards, isBankCardsLoadingValue = false) }
                    subscribeToTransactions()
                }
                .collect()
        }
    }

    private fun subscribeToTransactions() {
        viewModelScope.launch {
            cardInteractor
                .transactionFlow
                .distinctUntilChanged()
                .onEach { transactions ->
                    if (transactions.isEmpty()) cardInteractor.fetchTransactions(getState().getSelectedCardId())
                }
                .filterNotNull()
                .map { transactions ->
                    transactions
                        .sortedByDescending { it.data }
                        .sortedBy { it.category }
                        .groupBy { it.category }
                        .flatMap { map ->
                            listOf(
                                BankTransactionCategory(map.key),
                                *map.value.map { BankTransaction(it) }.toTypedArray()
                            )
                        }
                }
                .onEach { transactions ->
                    reduceState { getState().copy(transactionsValue = transactions, isBankCardsLoadingValue = false) }
                }
                .collect()
        }
    }

    fun updateSelectedCard(position: Int) {
        viewModelScope.launch {
            reduceState { getState().copy(selectedCardPositionValue = position) }
            val cardId = getState().cards.value.getOrNull(position)?.card?.id ?: return@launch
            cardInteractor.fetchTransactions(cardId)
        }
    }

    fun loadMoreTransactions(cardId: String) {

    }

}
