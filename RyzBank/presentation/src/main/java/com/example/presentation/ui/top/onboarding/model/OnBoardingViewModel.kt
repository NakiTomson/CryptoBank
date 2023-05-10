package com.example.presentation.ui.top.onboarding.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.presentation.navigation.nav_graph.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        val useer = savedStateHandle.get<Person>("user")
        Log.v("EJEJEJEJ", "re $useer")
    }
}