package com.example.presentation.ui.authentication.log_in.state

import com.example.entity.OnBoardingEntity
import com.example.presentation.core.BaseState
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel.ActionTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class SignInState(
    private val errorValue: Boolean = false,
    private val loadingValue: Boolean = false,
    private val emailErrorValue: Boolean = false,
    private val passwordErrorValue: Boolean = false,
) : BaseState {

    val error: StateFlow<Boolean> = MutableStateFlow(errorValue)
    val loading: StateFlow<Boolean> = MutableStateFlow(loadingValue)
    val emailWrong: StateFlow<Boolean> = MutableStateFlow(emailErrorValue)
    val passwordWrong: StateFlow<Boolean> = MutableStateFlow(passwordErrorValue)
}