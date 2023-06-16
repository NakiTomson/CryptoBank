package com.example.presentation.ui.top.onboarding

import android.widget.RatingBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.entity.CardEntity
import com.example.entity.TransactionEntity
import com.example.presentation.core_compose.color
import com.example.presentation.theme.Purple500
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import presentation.R
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun ViewPagerSliderPreview() {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        val pagerModifier = Modifier.fillMaxSize()
        val pageModifier = Modifier.fillMaxSize()

        ViewPagerSlider(
            pagerModifier,
            pageModifier,
            listOf(BankCard(CardEntity(), listOf(BankTransaction(TransactionEntity()))))
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerSlider(pagerModifier: Modifier, pageModifier: Modifier, cards: List<BankCard>) {

    val pagerState = rememberPagerState(
        initialPage = 0
    )

    HorizontalPager(
        pageCount = cards.size,
        state = pagerState,
        modifier = pagerModifier,
        beyondBoundsPageCount = 3
    ) { page ->
        Card(modifier = Modifier
            .then(pageModifier)
            .graphicsLayer {
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
            elevation = 0.dp,
            backgroundColor = Color.White
        ) {
            val card = cards[page].card
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(card.designCard.background.ifBlank { R.drawable.ic_desing_card })
                        .crossfade(true)
                        .placeholder(R.drawable.ic_desing_card)
                        .diskCachePolicy(CachePolicy.DISABLED)
                        .memoryCachePolicy(CachePolicy.DISABLED)
                        .build(),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterStart),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .align(Alignment.CenterStart)
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                )
                {
                    Image(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(40.dp),
                        painter = painterResource(card.paymentSystem.paySystem),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 30.dp),
                        text = "${card.paymentType.currency} ${card.balance}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = card.designCard.textColor.color
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 14.dp),
                        text = card.number, fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = card.designCard.textColor.color
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 14.dp),
                        text = card.holderName, fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = card.designCard.textColor.color
                    )
                }
            }
        }
    }
}
