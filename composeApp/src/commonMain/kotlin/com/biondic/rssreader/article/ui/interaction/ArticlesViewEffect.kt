package com.biondic.rssreader.article.ui.interaction

sealed interface ArticlesViewEffect {
    data class OpenExternalUrl(val externalUrl: String) : ArticlesViewEffect
    data object GoBackToSubscriptions : ArticlesViewEffect
}
