package com.buildweek.bbc.view.activities.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_asia.*
import kotlinx.android.synthetic.main.fragment_science.*
import org.jetbrains.anko.find

class ScienceFragment :Fragment(), LocalServerRecyclerAdapter.OnItemClickListener{

    lateinit var viewModel: MainViewModel
    lateinit var adapter: LocalServerRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar
    lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_science, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.scienceFragmentSwipeRefreshLayout)
        progressBar = view.findViewById<ProgressBar>(R.id.scienceFragmentProgressBar)
        youTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.youtubePlayer1)
        setRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            setRecyclerView()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        progressBar.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.newsByCategory("Science")
        viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
            adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
            inShotsRecyclerViewScience.adapter = adapter
            inShotsRecyclerViewScience.layoutManager = LinearLayoutManager(context)
            progressBar.visibility = View.GONE
            youTubePlayerView.visibility = View.VISIBLE

            val layoutAnimationController: LayoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
            inShotsRecyclerViewScience.layoutAnimation = layoutAnimationController

            adapter.notifyDataSetChanged()
            inShotsRecyclerViewScience.scheduleLayoutAnimation()

        })
    }

    override fun onItemClicked(article: LocalServerNewsItem) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}