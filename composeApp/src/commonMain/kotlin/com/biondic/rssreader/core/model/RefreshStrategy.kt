package com.biondic.rssreader.core.model

sealed interface RefreshStrategy {
    data object ReFetchLocalItems : RefreshStrategy
    data object SyncWithRemote : RefreshStrategy
}
