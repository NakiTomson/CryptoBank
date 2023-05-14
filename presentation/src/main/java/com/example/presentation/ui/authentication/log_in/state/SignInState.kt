package com.example.presentation.ui.authentication.log_in.state

import com.example.presentation.core.BaseState
import com.example.presentation.core_compose.CustomAlertState
import com.example.presentation.core_compose.rememberCustomAlertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class SignInState(
    private val errorValue: Boolean = false,
    private val loadingValue: Boolean = false,
    private val isValidEmailValue: Boolean = true,
    private val isValidPasswordValue: Boolean = true,
    private val alertStateValue: CustomAlertState = CustomAlertState()
) : BaseState {

    val isError: StateFlow<Boolean> = MutableStateFlow(errorValue)
    val isLoading: StateFlow<Boolean> = MutableStateFlow(loadingValue)
    val isValidEmail: StateFlow<Boolean> = MutableStateFlow(isValidEmailValue)
    val isValidPassword: StateFlow<Boolean> = MutableStateFlow(isValidPasswordValue)
    val alertState: StateFlow<CustomAlertState> = MutableStateFlow(alertStateValue)

    fun getAlert(): CustomAlertState = alertState.value
}