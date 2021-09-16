package com.example.news_app.model

import com.example.news_app.model.Data

data class InShortsNews(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)