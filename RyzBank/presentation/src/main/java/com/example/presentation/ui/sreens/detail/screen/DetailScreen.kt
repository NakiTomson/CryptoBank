package com.example.presentation.ui.sreens.detail.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun DetailScreen(
    onDetailClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onDetailClicked.invoke()
            }, contentAlignment = Alignment.Center
    ) {
        Column() {
            var text by rememberSaveable { mutableStateOf("") }

            Text(
                text = "Detail",
                color = Color.Red,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(30.dp))
            TextField(value = text, onValueChange = {
                text = it
            })
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun DetailScreenPreview() {
    DetailScreen()
}