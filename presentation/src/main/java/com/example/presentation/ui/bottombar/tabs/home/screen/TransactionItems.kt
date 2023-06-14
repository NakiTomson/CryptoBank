package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.core.FULL_FORMAT
import com.example.core.customFormatTime
import com.example.entity.CategoryTransactionType
import com.example.entity.PaymentCurrencyType
import com.example.entity.TransactionEntity
import com.example.entity.TransactionType
import com.example.presentation.theme.Green100
import com.example.presentation.theme.Red100
import com.example.presentation.theme.White100
import com.example.presentation.theme.White300
import presentation.R


@Composable
internal fun CategoryTransactionItem(modifier: Modifier = Modifier, category: CategoryTransactionType) {
    Box(modifier) {
        Text(
            text = stringResource(id = category.category),
            textAlign = TextAlign.Start,
            maxLines = 1,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun TransactionItem(
    modifier: Modifier,
    transaction: TransactionEntity,
    paymentType: PaymentCurrencyType,
    onTransactionClicked: () -> Unit = {},
) {
    Box(modifier.clickable {
        onTransactionClicked.invoke()
    }) {
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
                        append("$operator ${paymentType.currency}${transaction.amount}")
                    }
                },
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
