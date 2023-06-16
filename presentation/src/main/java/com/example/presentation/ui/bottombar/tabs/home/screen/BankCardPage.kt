package com.example.presentation.ui.bottombar.tabs.home.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.entity.CardEntity
import com.example.presentation.core_compose.color
import presentation.R
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
internal fun BankCardPagePreview() {
    Column {
        val pageModifier = Modifier.fillMaxWidth()
        BankCardPage(pageModifier, rememberPagerState(), 0, CardEntity())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BankCardPage(
    modifier: Modifier,
    pagerState: PagerState,
    page: Int,
    card: CardEntity
) {
    Card(
        modifier = Modifier
            .then(modifier)
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
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(card.designCard.background.ifBlank { R.drawable.ic_desing_card })
                    .crossfade(false)
                    .placeholder(R.drawable.ic_desing_card)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
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
