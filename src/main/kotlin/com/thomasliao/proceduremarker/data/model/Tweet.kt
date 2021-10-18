package com.thomasliao.proceduremarker.data.model

data class Tweet(
    val id: Int,
    val text: String,
    val author: String,
    val handle: String,
    val time: String,
    val authorImageId: Int = -1,
    val likesCount: Int,
    val commentsCount: Int,
    val retweetCount: Int = -1,
    val source: String = "",
    val tweetImageId: Int = 0
)