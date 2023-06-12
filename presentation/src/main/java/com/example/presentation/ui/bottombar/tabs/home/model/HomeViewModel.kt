package com.example.presentation.ui.bottombar.tabs.home.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.api.CardInteractor
import com.example.domain.api.UserInteractor
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.bottombar.tabs.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor,
    private val cardInteractor: CardInteractor,
) : StatefulScreenModel<HomeState, SideEffect>(HomeState()) {

    init {
        viewModelScope.launch {
            val user = userInteractor.getUser() ?: throw NullPointerException()
            val cards = cardInteractor.getUserCards(user.id)
            reduceState { getState().copy(userValue = user, cardsValue = cards) }
        }
    }
}