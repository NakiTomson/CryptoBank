package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.entity.CardEntity
import com.example.entity.Transaction
import com.example.entity.TransactionType
import com.example.entity.UserEntity
import com.example.presentation.theme.Black
import com.example.presentation.theme.Black300
import com.example.presentation.theme.Green100
import com.example.presentation.theme.White200
import com.example.presentation.theme.White300
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import presentation.R


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeRoute(
    onHomeClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val userState = state.value.user.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel, block = {

    })
    HomeScreen(onHomeClicked, { userState.value })
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    onHomeClicked: () -> Unit = {},
    user: () -> UserEntity? = { null },
    cards: List<CardEntity> = mutableListOf(CardEntity(), CardEntity(id = "2")),
) {
    Box(
        modifier = Modifier
            .background(White200)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), rememberLazyListState()) {
            stickyHeader {
                ConstraintLayout(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    val (
                        avatar,
                        welcome,
                        name,
                        pager,
                        addCard,
                        options,
                    ) = createRefs()

                    Image(
                        painter = painterResource(R.drawable.sample_icon),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .constrainAs(avatar) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(welcome) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start, 16.dp)
                            }
                            .clickable { onHomeClicked.invoke() },
                        text = "Welcome Back",
                        color = Green100,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(name) {
                                top.linkTo(welcome.bottom, 0.dp)
                                start.linkTo(welcome.start)
                            }
                            .clickable { onHomeClicked.invoke() },
                        text = "Anarda",
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    val pagerState: PagerState = rememberPagerState()
                    HorizontalPager(
                        count = cards.size,
                        modifier = Modifier
                            .constrainAs(pager) {
                                top.linkTo(name.bottom, 30.dp)
                                start.linkTo(parent.start)
                                end.linkTo(addCard.start)
                                width = Dimension.fillToConstraints
                            },
                        state = pagerState,
                        userScrollEnabled = true
                    ) {
                        BankCardPage(cards[pagerState.currentPage])
                    }

                    val brush = Brush.verticalGradient(colors = listOf(Black300, Black))

                    GradientButton(
                        gradient = brush,
                        modifier = Modifier
                            .constrainAs(addCard) {
                                linkTo(pager.top, pager.bottom)
                                end.linkTo(parent.end, 16.dp)
                                height = Dimension.fillToConstraints
                                width = Dimension.value(50.dp)
                            },
                        shape = RoundedCornerShape(20.dp),
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Transparent)
                        )
                    }
                    val modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .constrainAs(options) {
                            top.linkTo(pager.bottom, 20.dp)
                            bottom.linkTo(parent.bottom)
                        }
                    CardOptions(modifier)
                }
            }
            item {
                TitleTypeTransactionItem(Modifier, cards[0].transactions[0].type)
            }
            items(cards[0].transactions, key = { it.id }) {
//                when (it) {
//                    is TitleSearch -> TitleTypeTransactionItem(Modifier, it.type)
//                    is BusinessSearch -> TransactionItem(Modifier.fillMaxWidth(), it)
//                }
                TransactionItem(Modifier.fillMaxWidth(), it)
            }
//            item {
//                EmptyItem(isShowEmpty = isEmptyResult)
//            }
        }
    }
}

@Composable
fun BankCardPage(card: CardEntity) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            painter = painterResource(R.drawable.ic_desing_card),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.CenterStart)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        )
        {
            Image(painterResource(R.drawable.ic_visa), null, contentScale = ContentScale.FillWidth)
            Text("$${card.balance}", fontSize = 27.sp, fontWeight = FontWeight.Bold)
            Text(card.number, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(card.holderName, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun GradientButton(
    gradient: Brush,
    modifier: Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Button(
        shape = shape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick.invoke() })
    {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content.invoke(this)
        }
    }
}

@Composable
fun CardOptions(modifier: Modifier = Modifier) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(65.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Send",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Column {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(65.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_request),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Request",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Column {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(65.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_pay),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Pay",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column() {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(65.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = "More",
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun TitleTypeTransactionItem(modifier: Modifier = Modifier, type: TransactionType) {
    Box(modifier.padding(bottom = 20.dp, top = 16.dp)) {
        Text(
            text = type.name,
            textAlign = TextAlign.Start,
            maxLines = 1,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
private fun TransactionItem(
    modifier: Modifier = Modifier.fillMaxWidth(),
    transaction: Transaction = Transaction()
) {
    Box(modifier) {
        Row(
            modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(72.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_pay),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = transaction.name)
                Text(text = "28 Nov 2021 • 12:01 am ")
            }
            Text(text = "+ $234", Modifier.align(Alignment.CenterVertically))
        }
    }
}