package com.biondic.rssreader.core.utils

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString as getStringResource

class Dictionary {
    fun getString(
        resource: StringResource,
        vararg formatArgs: Any,
    ): String = runBlocking {
        getStringResource(resource, formatArgs)
    }

    fun getString(resource: StringResource): String = runBlocking {
        getStringResource(resource)
    }
}
