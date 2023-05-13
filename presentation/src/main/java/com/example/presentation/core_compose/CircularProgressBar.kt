package com.example.presentation.core_compose

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 5.dp,
    colorProgress: Color = Color.Blue,
    colorIndicator: Color = Color.LightGray,
    isLoading: () -> Boolean
) {
    if (isLoading()) {
        CircularProgressIndicator(
            modifier = modifier.drawBehind {
                drawCircle(
                    colorProgress,
                    radius = size.width / 2 - strokeWidth.toPx() / 2,
                    style = Stroke(strokeWidth.toPx())
                )
            },
            color = colorIndicator,
            strokeWidth = strokeWidth
        )
    }
}