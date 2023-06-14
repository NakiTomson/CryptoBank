package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entity.CardEntity
import presentation.R

@Composable
internal fun BankCardPage(modifier: Modifier, card: CardEntity) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
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
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(start = 14.dp),
                text = card.number, fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
            Text(
                modifier = Modifier
                    .padding(start = 14.dp),
                text = card.holderName, fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
        }
    }
}
