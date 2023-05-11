package com.example.presentation.ui.top.onboarding.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.presentation.core.BaseState
import com.example.presentation.core.EmptyState
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import com.example.presentation.ui.top.onboarding.state.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.parcel.Parcelize
import presentation.R
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    StatefulScreenModel<OnBoardingState, SideEffect>(OnBoardingState()) {


    enum class ActionTypes(val nameRes: Int) {
        HELLO(R.string.on_boarding_next_action),
        OK(R.string.on_boarding_next_want)
    }

    @Parcelize
    data class OnBoarding(
        val title: String = "",
        val text: String = "",
        @DrawableRes val mediaId: Int?
    ) : Parcelable
}