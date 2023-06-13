package com.example.presentation.ui.bottombar.tabs.home.state

import com.example.entity.AuthorizationType
import com.example.entity.UserEntity
import com.example.presentation.core.BaseState
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeState(
    private val userValue: UserEntity = UserEntity("", "", AuthorizationType.Null),
    private val cardsValue: List<BankCard> = listOf(),
    private val isUserLoadingValue: Boolean = true,
    private val isBankCardsLoadingValue: Boolean = true,
    private val isScreenLoadingValue: Boolean = isUserLoadingValue && isBankCardsLoadingValue,
) : BaseState {

    val user: StateFlow<UserEntity> = MutableStateFlow(userValue)

    val cards: StateFlow<List<BankCard>> = MutableStateFlow(cardsValue)

    val isUserLoading: StateFlow<Boolean> = MutableStateFlow(isUserLoadingValue)

    val isBankCardsLoading: StateFlow<Boolean> = MutableStateFlow(isBankCardsLoadingValue)

    val isScreenLoading: StateFlow<Boolean> = MutableStateFlow(isScreenLoadingValue)
}