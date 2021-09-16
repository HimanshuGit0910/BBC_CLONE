package com.example.news_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.buildweek.bbc.view.activities.ui.recyclerviews.LocalServerRecyclerAdapter
import com.example.news_app.R
import com.example.news_app.activity.DetailedNewsViewActivity
import com.example.news_app.model.LocalServerNewsItem
import com.example.news_app.viewmodel.MainViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_africa.*
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_top_stories.*


class PopularFragment : Fragment() , LocalServerRecyclerAdapter.OnItemClickListener{

    lateinit var viewModel: MainViewModel
//    lateinit var adapter: LocalServerRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }
    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.popularSwipeRefreshLayout)
        progressBar = view.findViewById<ProgressBar>(R.id.popularProgressBar)

        setRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            setRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        progressBar.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.worldNews()
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
//            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
          //  inShotsRecyclerViewPopular.adapter = adapter
            inShotsRecyclerViewPopular.layoutManager = LinearLayoutManager(context)
            progressBar.visibility = View.GONE

            val layoutAnimationController: LayoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
            inShotsRecyclerViewPopular.layoutAnimation = layoutAnimationController

           // adapter.notifyDataSetChanged()
            inShotsRecyclerViewPopular.scheduleLayoutAnimation()

        })
    }

    override fun onItemClicked(article: LocalServerNewsItem) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article",article)
        startActivity(intent)
    }

}