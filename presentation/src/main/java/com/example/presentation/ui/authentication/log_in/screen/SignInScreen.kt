package com.example.presentation.ui.authentication.log_in.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.theme.Black100
import com.example.presentation.theme.Black300
import com.example.presentation.theme.White100
import com.example.presentation.ui.authentication.log_in.model.SignInViewModel
import com.example.presentation.ui.top.splash.state.SplashState
import presentation.R


@Composable
@Preview(showBackground = true)
private fun SignUpPreview() {
    LogInScreen()
}

@Composable
fun LogInRoute(
    authorizationSuccess: () -> Unit = {},
    createNewAccountClicked: () -> Unit = {},
    forgotPasswordClicked: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    InternetConnectionLostScreen({ state.value == SplashState.Error }, {
    }, content = {
        LogInRoute(authorizationSuccess, createNewAccountClicked, forgotPasswordClicked, viewModel)
    }, Black100, Black100, Black100)
}


@Composable
fun LogInRoute(
    authorizationSuccess: () -> Unit = {},
    createNewAccountClicked: () -> Unit = {},
    forgotClicked: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel()
) {
    LogInScreen(authorizationSuccess)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    authorizationSuccess: () -> Unit = {},
    createNewAccountClicked: () -> Unit = {},
    forgotClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .background(Black300)
            .fillMaxSize()
            .drawWithCache {
                val rect = RoundRect(
                    rect = Rect(
                        offset = Offset(0f, size.width / 2f),
                        size = Size(size.width, size.height),
                    ),
                    topLeft = CornerRadius(150f, 150f),
                    topRight = CornerRadius(150f, 150f)
                )
                val path = Path()
                path.addRoundRect(rect)
                path.close()
                onDrawBehind {
                    drawPath(path, color = Black100)
                }
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var password by remember { mutableStateOf(TextFieldValue("")) }
            var email by remember { mutableStateOf(TextFieldValue("")) }

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(150.dp).weight(1f).padding(bottom = 0.dp)
            )

            Text(
                text = "Login",
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            TextField(
                value = email,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = {
                    Text(text = "Email", color = White100)
                },
                onValueChange = { it ->
                    email = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = White100,
                    unfocusedIndicatorColor = Black300,
                    containerColor = Color.Transparent,
                    errorIndicatorColor = Color.Red,
                    textColor = White100,
                ),
                maxLines = 1,
            )

            TextField(
                value = password,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 40.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = {
                    Text(text = "Passsword", color = White100)
                },
                onValueChange = { it ->
                    password = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = White100,
                    unfocusedIndicatorColor = Black300,
                    containerColor = Color.Transparent,
                    errorIndicatorColor = Color.Red,
                    textColor = White100
                ),
                maxLines = 1,
            )
            Button(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 45.dp)
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black300),
                onClick = {}
            ) {
                Text(text = "Log In", color = Color.White, maxLines = 1)
            }

            Text(
                text = "Or continue with",
                color = Black300,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                Modifier
                    .padding(bottom = 20.dp)
            ) {
                OutlinedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 8.dp)
                        .weight(1f)
                        .height(50.dp),
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black100),
                    border = BorderStroke(2.dp, Black300)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .size(18.dp)
                    )
                    Text(text = "Google", color = Black300, maxLines = 1)
                }

                OutlinedButton(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp)
                        .height(50.dp)
                        .weight(1f),
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black100),
                    border = BorderStroke(2.dp, Black300)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .size(24.dp)
                    )
                    Text(text = "Facebook", color = Black300, maxLines = 1)
                }
            }
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Black300, fontSize = 16.sp)) {
                        append(stringResource(R.string.not_have_account) + " ")
                    }
                    withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                        append(stringResource(R.string.create_now))
                    }
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clickable { }
            )
        }
    }
}
