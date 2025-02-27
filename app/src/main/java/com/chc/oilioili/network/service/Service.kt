package com.chc.oilioili.network.service

import com.chc.oilioili.network.Request
import com.chc.oilioili.network.api.UserInfoApi
import com.chc.oilioili.network.api.VideoApi

val videoService = Request.createService<VideoApi>()
val userService = Request.createService<UserInfoApi>()
