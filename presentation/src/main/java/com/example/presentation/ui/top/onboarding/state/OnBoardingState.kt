package com.example.presentation.ui.top.onboarding.state

import com.example.entity.OnBoardingEntity
import com.example.presentation.core.BaseState
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel.ActionTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class OnBoardingState(
    private val selectedPageValue: Int = 0,
    private val buttonTypeValue: ActionTypes = ActionTypes.SKIP,
    private val onBoardingScreensValue: List<OnBoardingEntity> = listOf(),
    private val errorValue: Boolean = false,
) : BaseState {

    val selectedPage: StateFlow<Int> = MutableStateFlow(selectedPageValue)
    val buttonType: StateFlow<ActionTypes> = MutableStateFlow(buttonTypeValue)
    val onBoardingScreens: StateFlow<List<OnBoardingEntity>> = MutableStateFlow(onBoardingScreensValue)
    val error: StateFlow<Boolean> = MutableStateFlow(errorValue)
}