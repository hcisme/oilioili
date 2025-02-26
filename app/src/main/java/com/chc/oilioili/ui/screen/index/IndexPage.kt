package com.chc.oilioili.ui.screen.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chc.oilioili.components.MovieCard
import com.chc.oilioili.navigation.MOVIE_PAGE
import com.chc.oilioili.utils.LocalNavController
import com.chc.oilioili.utils.getCompleteImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexPage(modifier: Modifier = Modifier) {
    val navHostController = LocalNavController.current
    val indexVM = viewModel<IndexViewModel>()

    LaunchedEffect(Unit) {
        if (indexVM.videoList.isEmpty()) {
            indexVM.getIndexVideoList()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            "点击搜索",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8F),
                        )
                    }
                },
                actions = {
                    IconButton({}) {
                        Icon(Icons.Default.Search, "搜索")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            items(indexVM.videoList) { item ->
                MovieCard(
                    modifier = Modifier,
                    coverUrl = getCompleteImage(url = item.videoCover),
                    title = item.videoName,
                    author = item.nickName,
                    onClick = {
                        navHostController.navigate("${MOVIE_PAGE}/${item.videoId}")
                    }
                )
            }
        }
    }
}
