package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.entity.AuthorizationType
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.UserEntity
import com.example.presentation.theme.Black
import com.example.presentation.theme.Black300
import com.example.presentation.theme.Green100
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import presentation.R

@OptIn(ExperimentalPagerApi::class)
@Preview(showSystemUi = true)
@Composable
private fun CardsScreenPreview() {
    val modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(top = 20.dp)

    val pagerState: PagerState = rememberPagerState()
    val cardEntity = CardEntity()
    val transactionType = listOf(
        BankTransactionCategory(CategoryTransactionType.Recent),
        *cardEntity.transactions.map { BankTransaction(it) }.toTypedArray()
    )
    val cards = mutableListOf(BankCard(cardEntity, transactionType))
    val user = UserEntity("1", "", AuthorizationType.Null, name = "Dmitry")
    LazyColumn(modifier = Modifier.fillMaxSize(), rememberLazyListState()) {
        item {
            CardsScreen(modifier, pagerState, cards = { cards }, user = { user })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun CardsScreen(
    modifier: Modifier,
    pagerState: PagerState,
    user: () -> UserEntity,
    cards: () -> List<BankCard>,
    openProfileClicked: () -> Unit = {},
    onDetailCardClicked: () -> Unit = {},
    onAddNewCardClicked: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            avatar,
            welcome,
            name,
            pager,
            addCard,
        ) = createRefs()

        val brush = Brush.verticalGradient(colors = listOf(Black300, Black))
        val pageModifier = Modifier.fillMaxSize()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.invoke().avatar)
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
                .clickable {
                    openProfileClicked.invoke()
                }
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
            text = user.invoke().name.toString(),
            color = Color.Black,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        HorizontalPager(
            count = cards.invoke().size,
            modifier = Modifier
                .constrainAs(pager) {
                    top.linkTo(name.bottom, 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(addCard.start)
                    width = Dimension.fillToConstraints
                }.clickable {
                    onAddNewCardClicked.invoke()
                },
            state = pagerState,
            userScrollEnabled = true
        ) {
            BankCardPage(pageModifier, cards.invoke()[pagerState.currentPage].card)
        }

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
            onClick = {
                onDetailCardClicked.invoke()
            }
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

