package com.example.presentation.core_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import presentation.R

@Composable
@Preview
private fun InternetConnectionLostScreenPreview() {
    InternetConnectionLostScreen()
}

@Composable
fun InternetConnectionLostScreen(
    isShowError: () -> Boolean = { false },
    tryAgainClicked: () -> Unit = {},
    content: @Composable () -> Unit = {},
    defStatusBarColor: Color = Color.White,
    errorStatusBarColor: Color = Color.Black,
    background: Color = Color.Black,
) {
    content()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = if (isShowError.invoke()) errorStatusBarColor else defStatusBarColor)
        systemUiController.setNavigationBarColor(defStatusBarColor)
    }
    if (isShowError.invoke()) {
        Box(Modifier.background(background), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, bottom = 100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_no_internet_cable),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_no_internet),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 200.dp, height = 200.dp)
                        .weight(3f)
                )

                Text(
                    text = stringResource(R.string.no_internet_connection),
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp).fontSize
                )

                Button(
                    onClick = tryAgainClicked, Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp)
                        .size(width = 200.dp, height = 50.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(text = stringResource(R.string.try_again))
                }
            }
        }
    }
}
