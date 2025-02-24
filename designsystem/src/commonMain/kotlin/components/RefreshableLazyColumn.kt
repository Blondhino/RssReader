package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshableLazyColumn(
    modifier: Modifier = Modifier,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Box(modifier = modifier) {
        val pullState = rememberPullRefreshState(
            refreshing = refreshing,
            onRefresh = onRefresh,
        )
        LazyColumn(
            modifier = Modifier.pullRefresh(pullState, enabled = true),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = content,
        )
        PullRefreshIndicator(refreshing, pullState, Modifier.align(Alignment.TopCenter))
    }
}
