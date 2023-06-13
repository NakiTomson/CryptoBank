package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.theme.White300
import presentation.R

@Composable
@Preview
fun CardOptionsPreview(modifier: Modifier = Modifier) {
    val cardOptionsModifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(top = 20.dp, bottom = 10.dp)
    CardOptions(cardOptionsModifier)
}
@Composable
fun CardOptions(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(65.dp)
                    .clickable { },
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
                modifier = Modifier
                    .size(65.dp)
                    .clickable { },
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
                modifier = Modifier
                    .size(65.dp)
                    .clickable { },
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
                modifier = Modifier
                    .size(65.dp)
                    .clickable { },
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
