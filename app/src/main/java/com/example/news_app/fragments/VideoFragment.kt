package com.example.news_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news_app.R
import com.example.news_app.recyclerviews.InshortsRecyclerAdapter


class VideoFragment : Fragment() {

    lateinit var adapter : InshortsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }
    companion object {
        fun newInstance() = VideoFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}