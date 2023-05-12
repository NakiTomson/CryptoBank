package com.example.presentation.ui.top.onboarding.model

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.OnBoardingInteractor
import com.example.domain.api.UserInteractor
import com.example.entity.OnBoardingEntity
import com.example.errors.ServerError
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.top.onboarding.event.OnBoardingSideEffect
import com.example.presentation.ui.top.onboarding.state.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import presentation.R
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val interceptor: OnBoardingInteractor,
    private val userInteractor: UserInteractor,
) : StatefulScreenModel<OnBoardingState, SideEffect>(OnBoardingState()) {

    private var onBoardingScreens: List<OnBoardingEntity> = emptyList()

    private var selectedPagePosition = 0

    init {
        launch {
            getScreens()
            subscribeToOnBoarding()
        }
    }

    private suspend fun getScreens() {
        try {
            interceptor.getOnBoardingScreens()
        } catch (e: ServerError) {
            reduceState { getCurrentState().copy(errorValue = true) }
        }
    }

    private suspend fun subscribeToOnBoarding() {
        interceptor.onBoardings.onEach {
            onBoardingScreens = it
            setButtonTypes(selectedPagePosition)
            reduceState { getCurrentState().copy(onBoardingScreensValue = onBoardingScreens) }
        }.collect()
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

    suspend fun onBackClicked() {
        when (selectedPagePosition) {
            0 -> postSideEffect(OnBoardingSideEffect.Back)
            else -> decrementPosition()
        }
    }

    private suspend fun incrementPosition() {
        selectedPagePosition += 1
        setButtonTypes(selectedPagePosition)
        reduceState { getCurrentState().copy(selectedPageValue = selectedPagePosition) }
    }

    private suspend fun decrementPosition() {
        selectedPagePosition -= 1
        setButtonTypes(selectedPagePosition)
        reduceState { getCurrentState().copy(selectedPageValue = selectedPagePosition) }
    }

    private suspend fun closeOnBoarding() {
        userInteractor.setNeedOnBoarding(false)
        postSideEffect(OnBoardingSideEffect.OpenRegister)
    }

    enum class ActionTypes(@StringRes val nameRes: Int) {
        SKIP(R.string.skip),
        OK(R.string.continue_now)
    }
}