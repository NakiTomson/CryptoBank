package com.example.presentation.ui.bottombar.tabs.home.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
}