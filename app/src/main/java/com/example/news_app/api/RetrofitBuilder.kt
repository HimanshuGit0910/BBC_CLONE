package com.example.news_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://ed213d91acfc.ngrok.io/api/bbc/"

    val newsInstance: ApiService
    init {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        newsInstance = retrofitBuilder.create(ApiService::class.java)
    }
}