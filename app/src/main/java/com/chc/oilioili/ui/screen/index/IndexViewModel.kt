package com.chc.oilioili.ui.screen.index

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.chc.oilioili.network.model.Category
import com.chc.oilioili.network.model.RecommendVideoData
import com.chc.oilioili.network.service.videoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IndexViewModel : ViewModel() {
    private var pageSize = 20
    var page = 1
    val categoryList = mutableStateListOf<Category>()
    val videoList = mutableStateListOf<RecommendVideoData>()

    suspend fun getCategoryList() {
        val result = withContext(Dispatchers.IO) {
            videoService.getCategoryList()
        }
        val list = result.data ?: listOf()
        categoryList.clear()
        categoryList.addAll(list)
    }

    suspend fun getIndexVideoList() {
        val result = withContext(Dispatchers.IO) {
            videoService.getNoRecommendVideo(page, pageSize)
        }
        val list = result.data?.list ?: listOf()
        videoList.clear()
        videoList.addAll(list)
    }
}