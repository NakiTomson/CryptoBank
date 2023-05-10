package com.example.presentation.ui.top.splash.model

import androidx.lifecycle.SavedStateHandle
import com.example.domain.api.UserInteractor
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val userInteractor: UserInteractor,
) : BaseViewModel() {

}
