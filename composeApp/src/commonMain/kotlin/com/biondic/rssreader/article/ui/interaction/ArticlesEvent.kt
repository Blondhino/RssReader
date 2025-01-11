package com.biondic.rssreader.article.ui.interaction

sealed interface ArticlesEvent {
    data object RefreshCalled : ArticlesEvent
    data class ArticleClick(val externalUrl: String) : ArticlesEvent
    data object TopBarBackClick : ArticlesEvent
}
