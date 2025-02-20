package model.rss

data class RssItem(
    val feedUrl: String,
    val title: String,
    val description: String,
    val mediaUrl: String,
    val link: String,
)
