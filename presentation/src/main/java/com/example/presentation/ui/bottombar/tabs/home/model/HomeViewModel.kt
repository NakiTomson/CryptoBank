package com.example.presentation.ui.bottombar.tabs.home.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.Dispatchers
import com.example.domain.api.CardInteractor
import com.example.domain.api.UserInteractor
import com.example.entity.CardEntity
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope {
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
        viewModelScope(dispatchers.io) {
            cardInteractor
                .cardsFlow
                .filterNotNull()
                .distinctUntilChanged()
                .map { cards ->
                    cards.toBankCards()
                }
                .onEach { cards ->
                    reduceState { getState().copy(cardsValue = cards, isBankCardsLoadingValue = false) }
                }
                .collect()
        }
    }

    fun loadMoreTransactions(cardId: String) {

    }

    private fun List<CardEntity>.toBankCards(): List<BankCard> {
        return this.map { card ->
            card to card.transactions
                .groupBy { it.category }
                .flatMap { map ->
                    listOf(BankTransactionCategory(map.key), *map.value.map { BankTransaction(it) }.toTypedArray())
                }
        }.map {
            BankCard(it.first, it.second)
        }
    }
}
