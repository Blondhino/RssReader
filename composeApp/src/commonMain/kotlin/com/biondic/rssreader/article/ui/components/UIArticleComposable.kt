package com.biondic.rssreader.article.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.biondic.rssreader.article.ui.model.UIArticleItem
import com.biondic.rssreader.core.ui.components.InfoCard
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun UIArticleComposable(
    modifier: Modifier = Modifier,
    article: UIArticleItem,
    onArticleClick: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
            .padding(vertical = 4.dp)
            .clickable { onArticleClick(article.externalUrl) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        InfoCard(
            modifier = Modifier.fillMaxWidth(if (article.imageUrl.isNotEmpty()) 0.65f else 1f),
            title = article.title,
            description = article.description,
        )

        CoilImage(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(5.dp)),
            imageModel = { article.imageUrl },
        )
    }
}
