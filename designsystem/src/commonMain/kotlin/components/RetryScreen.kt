package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rssreader.designsystem.Res
import com.rssreader.designsystem.retry_screen_button_text
import com.rssreader.designsystem.retry_screen_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun RetryScreen(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                stringResource(Res.string.retry_screen_message),
                modifier = Modifier.padding(16.dp),
            )
            Button(onClick = onRetryClick) {
                Text(text = stringResource(Res.string.retry_screen_button_text))
            }
        }
    }
}
