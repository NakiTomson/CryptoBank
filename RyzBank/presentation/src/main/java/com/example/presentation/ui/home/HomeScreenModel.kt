package com.example.presentation.ui.home

import com.example.presentation.core.EmptySideEffect
import com.example.presentation.core.EmptyState
import com.example.presentation.core.StatefulScreenModel
import javax.inject.Inject

class HomeScreenModel @Inject constructor() : StatefulScreenModel<EmptyState, EmptySideEffect>(EmptyState)