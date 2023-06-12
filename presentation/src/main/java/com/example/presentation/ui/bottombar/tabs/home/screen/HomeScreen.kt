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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.core.FULL_FORMAT
import com.example.core.customFormatTime
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.TransactionEntity
import com.example.entity.TransactionType
import com.example.entity.UserEntity
import com.example.presentation.theme.Black
import com.example.presentation.theme.Black300
import com.example.presentation.theme.Green100
import com.example.presentation.theme.Red100
import com.example.presentation.theme.White100
import com.example.presentation.theme.White300
import com.example.presentation.theme.White400
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import presentation.R


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    val cards = mutableListOf(CardEntity(), CardEntity(id = "2"))
    HomeScreen(cards = { cards })
}

@Composable
fun HomeRoute(
    onHomeClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val userState = state.value.user.collectAsStateWithLifecycle()
    val cardsState = state.value.cards.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel, block = {

    })
    HomeScreen({ userState.value }, { cardsState.value })
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    user: () -> UserEntity? = { null },
    cards: () -> List<CardEntity>,
) {
    if (cards.invoke().isEmpty()) return
    Box(
        modifier = Modifier
            .background(White400)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), rememberLazyListState()) {
            item {
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
                    ) = createRefs()

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.invoke()?.avatar)
                            .crossfade(true)
                            .placeholder(R.drawable.sample_icon)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentDescription = null,
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
                            .clickable { },
                        text = stringResource(id = R.string.welcome_back),
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
                            .clickable { },
                        text = user.invoke()?.name.toString(),
                        color = Color.Black,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    val pagerState: PagerState = rememberPagerState()
                    HorizontalPager(
                        count = cards.invoke().size,
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
                        BankCardPage(cards.invoke()[pagerState.currentPage])
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
                }
            }
            stickyHeader {
                val modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 20.dp, bottom = 10.dp)
                CardOptions(modifier)
            }
            item {
                TitleTypeTransactionItem(
                    Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp),
                    cards.invoke()[0].transactions[0].category
                )
            }
            items(cards.invoke()[0].transactions, key = { it.id }) {
//                when (it) {
//                    is TitleSearch -> TitleTypeTransactionItem(Modifier, it.type)
//                    is BusinessSearch -> TransactionItem(Modifier.fillMaxWidth(), it)
//                }
                TransactionItem(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp), it
                )
            }
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
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(card.paymentSystem.paySystem),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Text("${card.paymentType.currency}${card.balance}", fontSize = 27.sp, fontWeight = FontWeight.Bold)
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
                modifier = Modifier.size(65.dp).clickable {  },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp),
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
                modifier = Modifier.size(65.dp).clickable {  },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_request),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp),
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
                modifier = Modifier.size(65.dp).clickable {  },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_pay),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp),
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
                modifier = Modifier.size(65.dp).clickable {  },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                Image(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp),
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
private fun TitleTypeTransactionItem(modifier: Modifier = Modifier, type: CategoryTransactionType) {
    Box(modifier) {
        Text(
            text = type.name,
            textAlign = TextAlign.Start,
            maxLines = 1,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
private fun TransactionItem(
    modifier: Modifier = Modifier.fillMaxWidth(),
    transaction: TransactionEntity = TransactionEntity()
) {
    Box(modifier.clickable { }) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(72.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { })
            {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(transaction.media)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_pay)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(48.dp),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = transaction.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = transaction.data.customFormatTime(FULL_FORMAT),
                    fontSize = 12.sp,
                    color = White100,
                    fontWeight = FontWeight.Normal
                )
            }

            val operator = if (transaction.type == TransactionType.Income) "+" else "-"
            val color = if (transaction.type == TransactionType.Income) Green100 else Red100
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = color, fontSize = 16.sp)) {
                        append("$operator $${transaction.amount}")
                    }
                },
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
