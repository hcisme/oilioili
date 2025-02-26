package com.chc.oilioili.network.model

data class RecommendVideoData(
    val videoId: String,
    val videoCover: String,
    val videoName: String,
    val userId: String,
    val createTime: String,
    val lastUpdateTime: String,
    val categoryId: Long,
    val postType: Long,
    val originInfo: String? = null,
    val tags: String,
    val introduction: Any? = null,
    val interaction: String? = null,
    val duration: Long,
    val playCount: Long,
    val likeCount: Long,
    val danmuCount: Long,
    val commentCount: Long,
    val coinCount: Long,
    val collectCount: Long,
    val recommendType: Long,
    val lastPlayTime: String,
    val nickName: String,
    val avatar: String,
    val pcategoryId: Long
)

data class Category (
    val id: Long,
    val code: String,
    val name: String,
    val parentId: Long,
    val icon: Any? = null,
    val background: Any? = null,
    val sort: Long,
    val children: List<Category>
)
