package com.chc.oilioili.network.model

/**
 * 视频作者信息
 */
data class UserInfo (
    val nickName: String,
    val avatar: String,
    val sex: Long,
    val personIntroduction: String,
    val noticeInfo: String,
    val grade: Any? = null,
    val birthday: String,
    val school: Any? = null,
    val fansCount: Long,
    val focusCount: Long,
    val likeCount: Long,
    val playCount: Long,
    val haveFocus: Boolean,
    val theme: Long
)
