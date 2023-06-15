package com.example.core

import android.content.res.ColorStateList
import android.graphics.Color

import androidx.core.graphics.ColorUtils


fun String.toColorWithAlpha(alpha: Int = 255): ColorStateList {
    return ColorStateList.valueOf(ColorUtils.setAlphaComponent(Color.parseColor(this), alpha))
}

fun String.toColorWithAlphaOrNull(alpha: Int = 255): ColorStateList? = try {
    toColorWithAlpha(alpha)
} catch (e: Exception) {
    null
}

