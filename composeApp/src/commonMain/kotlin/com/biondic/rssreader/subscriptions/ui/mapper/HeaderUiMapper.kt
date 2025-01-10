package com.biondic.rssreader.subscriptions.ui.mapper

import com.biondic.rssreader.core.utils.Dictionary
import com.biondic.rssreader.subscriptions.ui.state.HeaderState
import com.biondic.rssreader.subscriptions.ui.state.HeaderStaticData
import rssreader.composeapp.generated.resources.Res
import rssreader.composeapp.generated.resources.header_button_add_text
import rssreader.composeapp.generated.resources.header_url_field_hint

class HeaderUiMapper(
    dictionary: Dictionary,
) {
    fun map(
        isLoading: Boolean = false,
        errorText: String? = null,
        text: String = "",
    ) = HeaderState(
        isLoading = isLoading,
        error = errorText,
        text = text,
        staticData = staticData,
    )

    private val staticData = HeaderStaticData(
        addButtonText = dictionary.getString(Res.string.header_button_add_text),
        hint = dictionary.getString(Res.string.header_url_field_hint),
    )
}
