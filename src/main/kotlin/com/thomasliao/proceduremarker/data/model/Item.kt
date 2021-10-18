package com.thomasliao.proceduremarker.data.model

data class Item(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageId: Int = -1,
    val source: String = "demo source"
)