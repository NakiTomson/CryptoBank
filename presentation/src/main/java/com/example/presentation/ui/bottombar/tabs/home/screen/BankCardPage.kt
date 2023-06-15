package com.example.presentation.ui.bottombar.tabs.home.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.entity.CardEntity
import com.example.presentation.core_compose.color
import presentation.R

@Composable
@Preview
internal fun BankCardPagePreview() {
    Column {
        val pageModifier = Modifier.fillMaxWidth()
        BankCardPage(pageModifier, CardEntity())
    }
}

@Composable
internal fun BankCardPage(modifier: Modifier, card: CardEntity) {
    Box(modifier = modifier) {
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
