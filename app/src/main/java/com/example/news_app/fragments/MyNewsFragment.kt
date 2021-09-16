package com.example.news_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buildweek.bbc.view.activities.ui.recyclerviews.LocalServerRecyclerAdapter
import com.example.news_app.R
import com.example.news_app.activity.DetailedNewsViewActivity
import com.example.news_app.model.LocalServerNewsItem
import com.example.news_app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_africa.*
import kotlinx.android.synthetic.main.fragment_my_news.*
import kotlinx.android.synthetic.main.fragment_top_stories.*


class MyNewsFragment : Fragment(), LocalServerRecyclerAdapter.OnItemClickListener {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: LocalServerRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_news, container, false)
    }

    companion object {
        fun newInstance() = MyNewsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mBtnLetsStart = view.findViewById<Button>(R.id.btnLetsGetStarted)
        val layout = view.findViewById<ConstraintLayout>(R.id.layoutMyNews)
        val showLayout = view.findViewById<LinearLayout>(R.id.layoutAfterClick)
        val btnGetNews = view.findViewById<Button>(R.id.btnGetMyNews)
        val recyclerView = view.findViewById<RecyclerView>(R.id.inShotsRecyclerViewMyNews)
        val progressBar = view.findViewById<ProgressBar>(R.id.topNewsProgressBarMyNews)
        val editText = view.findViewById<EditText>(R.id.etMyNews)
        mBtnLetsStart.setOnClickListener {
            layout.visibility = View.GONE
            showLayout.visibility = View.VISIBLE

            btnGetNews.setOnClickListener {
                val data = editText.text.toString()
                progressBar.visibility = View.VISIBLE
                viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.getNewsByTag(data)
                viewModel.getLocalServerNews().observe(viewLifecycleOwner, Observer {
                    adapter = context?.let { it1 -> LocalServerRecyclerAdapter(it1, it, this) }!!
                    inShotsRecyclerViewMyNews.adapter = adapter
                    inShotsRecyclerViewMyNews.layoutManager = LinearLayoutManager(context)
                    progressBar.visibility = View.GONE

                    val layoutAnimationController: LayoutAnimationController =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
                    inShotsRecyclerViewMyNews.layoutAnimation = layoutAnimationController

                    adapter.notifyDataSetChanged()
                    inShotsRecyclerViewMyNews.scheduleLayoutAnimation()

                })
            }

        }
    }

    override fun onItemClicked(article: LocalServerNewsItem) {
        val intent = Intent(activity, DetailedNewsViewActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }

}