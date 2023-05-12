package com.example.presentation.ui.top.onboarding.model

import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.OnBoardingInteractor
import com.example.entity.OnBoardingEntity
import com.example.errors.ServerError
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.top.onboarding.event.OnBoardingSideEffect
import com.example.presentation.ui.top.onboarding.state.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import presentation.R
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val interceptor: OnBoardingInteractor,
) : StatefulScreenModel<OnBoardingState, SideEffect>(OnBoardingState()) {

    private var onBoardingScreens: List<OnBoardingEntity> = emptyList()

    private var selectedPagePosition = 0

    init {
        launch {
            getScreens()
        }
    }

    private suspend fun getScreens() {
        try {
            onBoardingScreens = interceptor.getOnBoardingScreens()
            setButtonTypes(selectedPagePosition)
            reduceState { getCurrentState().copy(onBoardingScreensValue = onBoardingScreens) }
        } catch (e: ServerError) {
            reduceState { getCurrentState().copy(errorValue = true) }
        }
    }

    private suspend fun setButtonTypes(position: Int) {
        when (position) {
            ActionTypes.SKIP.ordinal -> reduceState { getCurrentState().copy(buttonTypeValue = ActionTypes.SKIP) }
            ActionTypes.OK.ordinal -> reduceState { getCurrentState().copy(buttonTypeValue = ActionTypes.SKIP) }
            else -> reduceState { getCurrentState().copy(buttonTypeValue = ActionTypes.OK) }
        }
    }

    fun onTryAgainClicked() {
        viewModelScope {
            reduceState { getCurrentState().copy(errorValue = false) }
            getScreens()
        }
    }

    suspend fun onNextClicked() {
        when (selectedPagePosition) {
            onBoardingScreens.lastIndex -> {
                postSideEffect(OnBoardingSideEffect.CheckPermission)
                closeOnBoarding()
            }

            else -> incrementPosition()
        }
    }

    private suspend fun incrementPosition() {
        selectedPagePosition += 1
        setButtonTypes(selectedPagePosition)
        reduceState { getCurrentState().copy(selectedPageValue = selectedPagePosition) }
    }

    private suspend fun closeOnBoarding() {
//        configRepository.isNeedShowOnBoarding = false
        postSideEffect(OnBoardingSideEffect.OpenRegister)
    }

    enum class ActionTypes(val nameRes: Int) {
        SKIP(R.string.skip),
        OK(R.string.continue_now)
    }
}