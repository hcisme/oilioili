package com.chc.oilioili.ui.screen.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chc.oilioili.utils.LocalNavController
import com.chc.oilioili.utils.getM3u8Url
import com.chc.videoplayer.Player

@Composable
fun MoviePage(id: String, modifier: Modifier = Modifier) {
    val navHostController = LocalNavController.current
    val movieVM = viewModel<MovieViewModel>()

    DisposableEffect(Unit) {
        onDispose {
            movieVM.resetStoreValue()
        }
    }

    LaunchedEffect(id) {
        movieVM.currentVideoId = id
        movieVM.getVideoDetail(id)
        movieVM.getVideoPList(id)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        Player(
            mediaUri = if (movieVM.currentFileInfo?.fileId == null)
                ""
            else
                getM3u8Url(movieVM.currentFileInfo!!.fileId),
            title = movieVM.currentFileInfo?.fileName ?: "",
            onClickBackButton = {
                navHostController.popBackStack()
            }
        )

        MvInfo()

        if (movieVM.videoPList.size > 1) {
            LazyRow {
                items(movieVM.videoPList) { item ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(120.dp)
                            .height(56.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(
                                2.dp,
                                if (item.fileId == movieVM.currentFileInfo?.fileId)
                                    MaterialTheme.colorScheme.inversePrimary
                                else
                                    MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                movieVM.currentFileInfo = item
                            }
                            .padding(8.dp)
                    ) {
                        Text(
                            item.fileName,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}
