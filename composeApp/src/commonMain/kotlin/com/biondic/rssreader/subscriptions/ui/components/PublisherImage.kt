package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun PublisherImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    backgroundColor: Color = Color.LightGray,
) {
    CoilImage(
        modifier = modifier.clip(CircleShape)
            .border(width = 2.dp, color = Color.Gray, shape = CircleShape),
        imageModel = { imageUrl },
        failure = {
            PublisherImagePlaceholder(
                modifier = modifier,
                title = title,
                backgroundColor = backgroundColor,
            )
        },
        loading = {
            PublisherImagePlaceholder(
                modifier = modifier,
                title = title,
                backgroundColor = backgroundColor,
            )
        },
    )
}

@Composable
private fun PublisherImagePlaceholder(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    title: String = " ",
) {
    Box(modifier = modifier.background(backgroundColor)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title.getOrElse(0) { ' ' }.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
    }
}
