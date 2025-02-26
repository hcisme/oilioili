package com.chc.oilioili.network.api

import com.chc.oilioili.network.BaseResult
import com.chc.oilioili.network.PaginationData
import com.chc.oilioili.network.model.Category
import com.chc.oilioili.network.model.RecommendVideoData
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("/api/web/video/getNoRecommendVideo")
    suspend fun getNoRecommendVideo(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("categoryId") categoryId: Long? = null
    ): BaseResult<PaginationData<RecommendVideoData>>

    @GET("/api/web/category/list")
    suspend fun getCategoryList(): BaseResult<List<Category>>
}
