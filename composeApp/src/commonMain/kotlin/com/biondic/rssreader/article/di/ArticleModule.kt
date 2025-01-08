package com.biondic.rssreader.article.di

import com.biondic.rssreader.article.ui.ArticleViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun articleModule() = module {
    factoryOf(::ArticleViewModel)
}
