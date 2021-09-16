package com.example.news_app.model

import java.io.Serializable

data class LocalServerNewsItem (
    val author: String,
    val category: String,
    val catid: Int,
    val content: String,
    val created_at: String,
    val dateofnews: String,
    val frontimage: String,
    val image1: String,
    val image2: String,
    val mainheading: String,
    val newsId: Int,
    val newsid: Int,
    val regid: Int,
    val region: String,
    val subheading1: String,
    val subheading2: String,
    val tags: String,
    val type: String,
    val video: String
): Serializable