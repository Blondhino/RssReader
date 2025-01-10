package com.biondic.rssreader.subscriptions.ui.mapper

import arrow.core.Either.Right
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData

class MergeRemoteDataIntoLocalItems {
    operator fun invoke(
        remoteData: SubscriptionData.Remote,
        currentItems: List<Subscription>,
    ): MutableList<Subscription> {
        val mergedItems = currentItems.toMutableList()
        remoteData.results.forEach { result ->
            if (result is Right) {
                val remoteItem = result.value
                val index = mergedItems.indexOfFirst { it.url == remoteItem.url }

                if (index >= 0) {
                    mergedItems[index] = remoteItem.copy(
                        isFavorite = mergedItems[index].isFavorite,
                    )
                } else {
                    mergedItems.add(remoteItem)
                }
            }
        }
        return mergedItems
    }
}
