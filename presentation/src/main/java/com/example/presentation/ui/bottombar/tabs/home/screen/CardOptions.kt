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
import androidx.compose.ui.res.stringResource
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
fun CardOptions(
    modifier: Modifier = Modifier,
    sendCardClicked: () -> Unit = {},
    requestCardClicked: () -> Unit = {},
    payCardClicked: () -> Unit = {},
    moreOptionsClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Button(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(60.dp)
                    .clickable { },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { sendCardClicked.invoke() })
            {
                Image(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.send),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Column {
            Button(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(60.dp)
                    .clickable { },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { requestCardClicked.invoke() })
            {
                Image(
                    painter = painterResource(R.drawable.ic_request),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.request),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Column {
            Button(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(60.dp)
                    .clickable { },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { payCardClicked.invoke() })
            {
                Image(
                    painter = painterResource(R.drawable.ic_pay),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.pay),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column() {
            Button(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(60.dp)
                    .clickable { },
                colors = ButtonDefaults.buttonColors(backgroundColor = White300),
                contentPadding = PaddingValues(),
                onClick = { moreOptionsClicked.invoke() })
            {
                Image(
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.more),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
