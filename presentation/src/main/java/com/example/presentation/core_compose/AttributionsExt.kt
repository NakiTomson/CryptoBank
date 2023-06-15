package com.example.presentation.core_compose

import android.content.res.ColorStateList
import androidx.compose.ui.graphics.Color
import com.example.core.toColorWithAlpha

val String.color
    get() = try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: Exception) {
        Color.Black
    }