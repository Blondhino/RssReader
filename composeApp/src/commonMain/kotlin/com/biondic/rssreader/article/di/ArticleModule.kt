package com.biondic.rssreader.article.di

import com.biondic.rssreader.article.data.datasource.DatabaseArticlesDatasource
import com.biondic.rssreader.article.data.datasource.NetworkArticlesDatasource
import com.biondic.rssreader.article.data.mapper.ArticleEntityMapper
import com.biondic.rssreader.article.data.mapper.RssFeedToArticlesMapper
import com.biondic.rssreader.article.data.repo.OfflineFirstArticleRepository
import com.biondic.rssreader.article.domain.datasource.LocalArticlesDatasource
import com.biondic.rssreader.article.domain.datasource.RemoteArticlesDatasource
import com.biondic.rssreader.article.domain.repo.ArticleRepository
import com.biondic.rssreader.article.domain.usecase.ExtractArticles
import com.biondic.rssreader.article.domain.usecase.GetArticles
import com.biondic.rssreader.article.ui.ArticleViewModel
import com.biondic.rssreader.article.ui.mapper.ArticlesUiStateMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun articleModule() = module {
    factoryOf(::ArticleViewModel)
    factoryOf(::OfflineFirstArticleRepository) bind ArticleRepository::class
    factoryOf(::NetworkArticlesDatasource) bind RemoteArticlesDatasource::class
    factoryOf(::DatabaseArticlesDatasource) bind LocalArticlesDatasource::class
    factoryOf(::RssFeedToArticlesMapper)
    factoryOf(::ArticlesUiStateMapper)
    factoryOf(::ArticleEntityMapper)
    factoryOf(::ExtractArticles)
    factoryOf(::GetArticles)
}
