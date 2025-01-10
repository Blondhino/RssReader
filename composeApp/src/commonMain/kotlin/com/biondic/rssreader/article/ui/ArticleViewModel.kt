package com.biondic.rssreader.article.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.biondic.rssreader.article.domain.usecase.GetArticles
import com.biondic.rssreader.core.model.RefreshStrategy.SyncWithRemote
import kotlinx.coroutines.launch

class ArticleViewModel(
    getArticles: GetArticles,
) : ScreenModel {
    init {
        screenModelScope.launch {
            getArticles(
                subscriptionUrl = "https://www.france24.com/en/rss",
                refreshStrategy = SyncWithRemote,
                refreshingCompleted = {
                    println("Refreshing completed")
                },
            ).collect {
                println(it)
            }
        }
    }

    fun getScreenTitle() = "This is the Article Screen"
}
