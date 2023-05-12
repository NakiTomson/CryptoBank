package com.example.presentation.ui.authentication.log_in.model

import androidx.lifecycle.SavedStateHandle
import com.example.presentation.core.BaseState
import com.example.presentation.core.EmptyState
import com.example.presentation.core.SideEffect
import com.example.presentation.core.StatefulScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : StatefulScreenModel<BaseState, SideEffect>(EmptyState) {

}