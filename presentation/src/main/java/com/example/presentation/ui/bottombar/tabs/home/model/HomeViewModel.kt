package com.example.presentation.ui.bottombar.tabs.home.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.api.UserInteractor
import com.example.entity.UserEntity
import com.example.presentation.core.BaseViewModel
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.bottombar.tabs.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userInteractor: UserInteractor
) : StatefulScreenModel<HomeState, SideEffect>(HomeState()) {


    init {
        viewModelScope {
            val user = userInteractor.getUser()
            reduceState { getState().copy(userValue = user) }
        }
    }

}