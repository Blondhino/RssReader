package com.biondic.rssreader.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmittableText(
    modifier: Modifier = Modifier,
    text: String,
    errorText: String?,
    onTextChange: (String) -> Unit,
    hint: String,
    submitButtonText: String,
    isLoading: Boolean = false,
    onSubmit: (currentValue: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Column(modifier = modifier.fillMaxWidth(0.8f)) {
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = text,
                onValueChange = onTextChange,
                placeholder = { Text(hint) },
                isError = errorText != null,
                enabled = !isLoading,
            )
            AnimatedVisibility(visible = isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(top = 3.dp))
            }
            AnimatedVisibility(visible = errorText != null) {
                Text(modifier = Modifier.padding(horizontal = 8.dp), text = errorText.orEmpty())
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSubmit(text) },
            enabled = !isLoading,
        ) {
            Text(submitButtonText)
        }
    }
}
