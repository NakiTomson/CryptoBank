package com.example.presentation.ui.bottombar.tabs.home.state

import com.example.entity.UserEntity
import com.example.presentation.core.BaseState
import com.example.presentation.core_compose.CustomAlertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeState(
    private val userValue: UserEntity? = null,
) : BaseState {

    val user: StateFlow<UserEntity?> = MutableStateFlow(userValue)
}