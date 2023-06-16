package com.example.presentation.ui.bottombar.tabs.home.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.Dispatchers
import com.example.domain.api.CardInteractor
import com.example.domain.api.UserInteractor
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        viewModelScope {
            loadUser()
        }
    }

    private suspend fun loadUser() {
        val user = userInteractor.getUser() ?: throw NullPointerException()
        reduceState { getState().copy(userValue = user, isUserLoadingValue = false) }
        loadBankCards(user.id)
    }

    private suspend fun loadBankCards(userId: String) {
        val cardItem = withContext(dispatchers.io) {
            val cards = cardInteractor.getUserCards(userId)
            cards.map { card ->
                card to card.transactions
                    .groupBy { it.category }
                    .flatMap { map ->
                        listOf(BankTransactionCategory(map.key), *map.value.map { BankTransaction(it) }.toTypedArray())
                    }
            }.map {
                BankCard(it.first, it.second)
            }
        }
        reduceState { getState().copy(cardsValue = cardItem, isBankCardsLoadingValue = false) }
    }
}
