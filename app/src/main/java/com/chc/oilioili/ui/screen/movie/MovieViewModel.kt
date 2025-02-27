package com.chc.oilioili.ui.screen.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chc.oilioili.network.model.UserInfo
import com.chc.oilioili.network.model.VideoDetail
import com.chc.oilioili.network.model.VideoPData
import com.chc.oilioili.network.service.videoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    var currentVideoId by mutableStateOf<String?>(null)
    val videoPList = mutableStateListOf<VideoPData>()
    var currentVideoDetail by mutableStateOf<VideoDetail?>(null)
    var currentFileInfo by mutableStateOf<VideoPData?>(null)
    var currentVideoUserInfo by mutableStateOf<UserInfo?>(null)

    suspend fun getVideoDetail(vid: String) {
        val result = withContext(Dispatchers.IO) {
            videoService.getVideoDetail(vid)
        }
        currentVideoDetail = result.data?.videoInfo
        getUserInfo()
    }

    suspend fun getVideoPList(vid: String) {
        val result = withContext(Dispatchers.IO) {
            videoService.getVideoPList(vid)
        }
        val list = result.data ?: listOf()
        if (list.isNotEmpty()) {
            currentFileInfo = list.first()
        }
        videoPList.addAll(list)
    }

    suspend fun getUserInfo() {
        if (currentVideoDetail?.userId == null) {
            return
        }
        val result = withContext(Dispatchers.IO) {
            videoService.getUserInfo(currentVideoDetail!!.userId)
        }
        currentVideoUserInfo = result.data
    }
}