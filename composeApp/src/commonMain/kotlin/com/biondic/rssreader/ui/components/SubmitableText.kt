package com.biondic.rssreader.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun SubmittableText(
    modifier: Modifier = Modifier,
    text: String,
    errorText: String?,
    onTextChange: (String) -> Unit,
    hint: String,
    isLoading: Boolean = false,
    onTrailingIconClick: (currentValue: String) -> Unit,
    shape: Shape = RoundedCornerShape(25),
) {
    val errorColor = MaterialTheme.colorScheme.error
    val unfocusedColor = MaterialTheme.colorScheme.outline
    val focusedColor = MaterialTheme.colorScheme.secondary
    val isError = errorText != null
    var focused by remember { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        targetValue = when {
            isError -> errorColor
            focused -> focusedColor
            else -> unfocusedColor
        },
    )
    val iconAngle by animateFloatAsState(if (isError) 45f else 0f)
    Column {
        Row(
            modifier =
            modifier.fillMaxWidth()
                .clip(shape)
                .border(width = 2.dp, color = borderColor, shape = shape),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(0.86f)
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .onFocusChanged { focused = it.isFocused },
                value = text,
                onValueChange = onTextChange,
                maxLines = 1,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.outline,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    innerTextField()
                },
            )
            Box(
                modifier = Modifier
                    .clip(shape)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(borderColor)
                    .clickable { onTrailingIconClick(text) },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.rotate(iconAngle),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceBright,
                )
            }
        }

        AnimatedVisibility(visible = isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                color = borderColor,
                strokeCap = StrokeCap.Round,
            )
        }

        AnimatedVisibility(visible = isError) {
            errorText?.let {
                Text(
                    text = it,
                    color = errorColor,
                    modifier = Modifier.padding(start = 10.dp),
                )
            }
        }
    }
}
