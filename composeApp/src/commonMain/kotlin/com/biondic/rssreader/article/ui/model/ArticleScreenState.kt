package com.biondic.rssreader.article.ui.model

sealed interface ArticleScreenState {
    data object Loading : ArticleScreenState
    data object Error : ArticleScreenState
    data class Content(
        val screenTitle: String,
        val isRefreshing: Boolean,
        val articles: List<UIArticleItem>,
    ) : ArticleScreenState
}
