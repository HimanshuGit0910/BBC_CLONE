package com.example.news_app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.news_app.api.RetrofitBuilder
import com.example.news_app.model.LocalServerNews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object MainRepository {

    private var _newsLocalServerList: MutableLiveData<LocalServerNews> = MutableLiveData()

    fun getLocalServerNews(): MutableLiveData<LocalServerNews> {
        return _newsLocalServerList
    }

    fun newsByRegion(region: String) {
        val news = RetrofitBuilder.newsInstance.getNewsByRegion(region)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)
                }

                override fun onResponse(
                    call: Call<LocalServerNews>,
                    response: Response<LocalServerNews>
                ) {
                    val news = response.body()
                    if (news != null) {
                        _newsLocalServerList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun worldNews() {
        val news = RetrofitBuilder.newsInstance.getWorldNews()
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<LocalServerNews>,
                    response: Response<LocalServerNews>
                ) {
                    _newsLocalServerList.postValue(response.body())
                }

            })
        }
    }

    fun newsByTag(tag: String) {
        val news = RetrofitBuilder.newsInstance.newsByTag(tag)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)
                }

                override fun onResponse(
                    call: Call<LocalServerNews>,
                    response: Response<LocalServerNews>
                ) {
                    val news = response.body()
                    if (news != null) {
                        _newsLocalServerList.postValue(response.body())
                    }
                }
            })
        }
    }


    fun newsByCategory(category: String) {
        val news = RetrofitBuilder.newsInstance.getNewsByCategory(category)
        CoroutineScope(Dispatchers.IO).launch {
            news.enqueue(object : retrofit2.Callback<LocalServerNews> {
                override fun onFailure(call: Call<LocalServerNews>, t: Throwable) {
                    Log.d("Sumeet", "Error in fetching news", t)

                }

                override fun onResponse(
                    call: Call<LocalServerNews>,
                    response: Response<LocalServerNews>
                ) {
                    val news = response.body()
                    if (news != null) {
                        _newsLocalServerList.postValue(response.body())
                    }
                }
            })
        }
    }

}