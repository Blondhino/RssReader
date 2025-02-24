import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun AppTheme(content: @Composable () -> Unit) = MaterialTheme(
    content = content,
    colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,
)
