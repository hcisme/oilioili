package com.chc.oilioili.network.api

import com.chc.oilioili.network.BaseResult
import com.chc.oilioili.network.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoApi {
    @GET("/api/web/uHome/getUserInfo")
    suspend fun getUserInfo(@Query("userId") userId: String): BaseResult<UserInfo>
}
