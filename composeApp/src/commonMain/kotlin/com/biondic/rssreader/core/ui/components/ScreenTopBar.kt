package com.biondic.rssreader.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    title: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.White),
    ) {
        Icon(
            modifier = Modifier.padding(8.dp)
                .clickable(rippleEffectVisible = false) { onBackClick() }
                .align(Alignment.CenterStart),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
        )

        Text(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.6f).basicMarquee(),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}
