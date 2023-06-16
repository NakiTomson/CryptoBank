package com.example.presentation.ui.top.onboarding.screen

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.entity.OnBoardingEntity
import com.example.presentation.core.EmptySideEffect
import com.example.presentation.core_compose.BackPressHandler
import com.example.presentation.core_compose.InternetConnectionLostScreen
import com.example.presentation.theme.Black100
import com.example.presentation.theme.Black200
import com.example.presentation.theme.White100
import com.example.presentation.ui.top.onboarding.event.OnBoardingSideEffect
import com.example.presentation.ui.top.onboarding.model.OnBoardingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import presentation.R

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
private fun OnBoardingScreenPreview() {
    val text = stringResource(R.string.on_boarding_next_action)
    val mock = OnBoardingEntity(text, text, "https://i.ibb.co/VNycWc0/screen-One.jpg")
    OnBoardingScreen(onBoardings = { listOf(mock, mock) })
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OnBoardingRoute(
    openRegisterRoute: () -> Unit = {},
    openNavigationRoute: () -> Unit = {},
    popBackStack: () -> Unit = {},
    supportNetworkErrorScreen: Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val error = state.value.error.collectAsStateWithLifecycle()
    InternetConnectionLostScreen({ error.value }, {
        viewModel.onTryAgainClicked()
    }, content = {
        OnBoardingRoute(openRegisterRoute, openNavigationRoute, popBackStack, viewModel)
    }, Black100, Black100, Black100)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalPermissionsApi::class)
@Composable
fun OnBoardingRoute(
    openRegisterRoute: () -> Unit = {},
    openNavigationRoute: () -> Unit = {},
    popBackStack: () -> Unit = {},
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val events = viewModel.sideEffectFlow.collectAsStateWithLifecycle(EmptySideEffect)
    val onBoardings = state.value.onBoardingScreens.collectAsStateWithLifecycle()
    val buttonType = state.value.buttonType.collectAsStateWithLifecycle()
    val selectedPage = state.value.selectedPage.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    BackPressHandler(onBackPressed = {
        coroutineScope.launch { viewModel.onBackClicked() }
    })

    LaunchedEffect(key1 = viewModel, block = {
        snapshotFlow { selectedPage.value }
            .filter { selectedPage.value != -1 }
            .collect {
                pagerState.animateScrollToPage(it)
            }
    })

    LaunchedEffect(viewModel) {
        snapshotFlow { events.value }
            .collect {
                when (it) {
                    OnBoardingSideEffect.CheckPermission -> {
                        if (locationPermissionState.status.isGranted) return@collect
                        locationPermissionState.launchPermissionRequest()
                    }

                    OnBoardingSideEffect.OpenRegister -> openRegisterRoute.invoke()
                    OnBoardingSideEffect.OpenNavigation -> openNavigationRoute.invoke()
                    OnBoardingSideEffect.Back -> popBackStack.invoke()
                }
            }
    }

    OnBoardingScreen(
        onBoardings = { onBoardings.value },
        actionTypes = { buttonType.value },
        pagerState = pagerState
    ) {
        coroutineScope.launch { viewModel.onNextClicked() }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onBoardings: () -> List<OnBoardingEntity>,
    actionTypes: () -> OnBoardingViewModel.ActionTypes = { OnBoardingViewModel.ActionTypes.OK },
    pagerState: PagerState = rememberPagerState(),
    onNextClicked: () -> Unit = {},
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black100)
        ) {
            OnBoardingScreenPager(
                onBoardings.invoke(),
                pagerState,
                Modifier.weight(1f)
            )
            OutlinedButton(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 100.dp, bottom = 50.dp)
                    .height(50.dp),
                onClick = { onNextClicked.invoke() },
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Black200),
                border = BorderStroke(0.dp, Black200)
            ) {
                Text(text = stringResource(actionTypes.invoke().nameRes), color = Color.White)
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.TopCenter),
            activeColor = Color.White,
            inactiveColor = Color.Gray,
            indicatorWidth = 20.dp,
            indicatorHeight = 5.dp,
            spacing = 5.dp
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreenPager(
    onBoardings: List<OnBoardingEntity>,
    state: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        onBoardings.size, state = state, modifier = modifier.fillMaxSize(), userScrollEnabled = false
    ) {page->
        OnBoardingPage(onBoardings[page])
    }
}

@Composable
fun OnBoardingPage(item: OnBoardingEntity) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.media)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED).build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 60.dp)
                .weight(3f)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
                .align(alignment = Alignment.Start),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = item.text,
            color = White100,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(alignment = Alignment.Start)
                .padding(start = 50.dp, end = 50.dp),
            textAlign = TextAlign.Justify
        )
    }
}
