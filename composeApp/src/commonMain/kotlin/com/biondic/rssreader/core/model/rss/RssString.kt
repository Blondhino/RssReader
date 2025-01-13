package com.biondic.rssreader.core.model.rss

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.biondic.rssreader.core.utils.decodeHtmlEntities
import com.biondic.rssreader.core.utils.extractPlainTextFromHtml
import kotlin.jvm.JvmInline

@JvmInline
value class RssString private constructor(private val value: String) {
    companion object {
        operator fun invoke(value: String): Either<RssStringFormatError, RssString> = either {
            ensure(value.contains(RSS_CHANEL_START)) { RssStringFormatError }
            ensure(value.contains(RSS_CHANEL_END)) { RssStringFormatError }
            ensure(value.contains(RSS_ITEM_START)) { RssStringFormatError }
            ensure(value.contains(RSS_ITEM_END)) { RssStringFormatError }
            RssString(decodeHtmlEntities(value))
        }

        fun RssString.feed() = RssFeed(
            subscriptionInfo = subscriptionInfo(),
            items = feedItems(),
        )
    }

    private fun RssString.subscriptionInfo() = RssSubscriptionInfo(
        title = value.extractTitle(),
        description = value.extractDescription(),
        imageUrl = value.extractFeedImageUrl(),
        link = value.extractLink(),
    )

    private fun RssString.feedItems(): List<RssItem> {
        val rssItems = mutableListOf(value.substringAfter(RSS_ITEM_START).extractItem())
        var remainingItems = value.substringAfter(RSS_ITEM_END)
        while (remainingItems.contains(RSS_ITEM_START)) {
            val item = remainingItems.extractItem()
            rssItems.add(item)
            remainingItems = remainingItems.substringAfter(RSS_ITEM_END)
        }
        return rssItems
    }

    private inline fun String.extractItem() = RssItem(
        title = extractTitle(),
        description = extractDescription(),
        link = extractLink(),
        mediaUrl = extractMediaUrl(),
        feedUrl = extractLink(),
    )

    private fun String.extractMediaUrl() = this
        .substringAfter(RSS_MEDIA_START, OR_EMPTY)
        .substringAfter(RSS_MEDIA_URL_START)
        .substringBefore(RSS_MEDIA_URL_END)

    private fun String.extractTitle() = this
        .substringAfter(RSS_TITLE_START)
        .substringBefore(RSS_TITLE_END)
        .substringAfter(RSS_CDATA_START)
        .substringBefore(RSS_CDATA_END)
        .extractPlainTextFromHtml()
        .trim()

    private fun String.extractDescription() = this
        .substringAfter(RSS_DESCRIPTION_START, OR_EMPTY)
        .substringBefore(RSS_DESCRIPTION_END)
        .substringAfter(RSS_CDATA_START)
        .substringBefore(RSS_CDATA_END)
        .extractPlainTextFromHtml()
        .trim()

    private fun String.extractFeedImageUrl() = this
        .substringBefore(RSS_ITEM_START)
        .substringAfter(RSS_IMAGE_START, OR_EMPTY)
        .substringBefore(RSS_IMAGE_END)
        .substringAfter(RSS_URL_START)
        .substringBefore(RSS_URL_END)

    private fun String.extractLink() = this
        .substringAfter(RSS_LINK_START)
        .substringBefore(RSS_LINK_END)
        .substringAfter(RSS_CDATA_START)
        .substringBefore(RSS_CDATA_END)
}

private const val RSS_TITLE_START = "<title>"
private const val RSS_TITLE_END = "</title>"
private const val RSS_CDATA_END = "]]>"
private const val RSS_CDATA_START = "<![CDATA["
private const val RSS_DESCRIPTION_START = "<description>"
private const val RSS_DESCRIPTION_END = "</description>"
private const val RSS_URL_START = "<url>"
private const val RSS_URL_END = "</url>"
private const val RSS_LINK_START = "<link>"
private const val RSS_LINK_END = "</link>"
private const val RSS_MEDIA_START = "<media"
private const val RSS_MEDIA_URL_START = "url=\""
private const val RSS_MEDIA_URL_END = "\""
private const val RSS_ITEM_START = "<item>"
private const val RSS_ITEM_END = "</item>"
private const val RSS_CHANEL_START = "<channel>"
private const val RSS_CHANEL_END = "</channel>"
private const val RSS_IMAGE_START = "<image>"
private const val RSS_IMAGE_END = "</image>"
private const val OR_EMPTY = ""
