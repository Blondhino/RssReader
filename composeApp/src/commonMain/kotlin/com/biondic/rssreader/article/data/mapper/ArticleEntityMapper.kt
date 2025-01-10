package com.biondic.rssreader.article.data.mapper

import com.biondic.rssreader.ArticleEntity
import com.biondic.rssreader.article.domain.model.Article

class ArticleEntityMapper {
    fun map(entity: ArticleEntity): Article = Article(
        title = entity.title,
        description = entity.description,
        imageUrl = entity.imageUrl,
        externLink = entity.externalUrl,
        parentUrl = entity.subscriptionUrl,
    )
}
