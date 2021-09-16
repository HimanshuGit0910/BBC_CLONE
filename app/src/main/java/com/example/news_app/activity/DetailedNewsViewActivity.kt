package com.example.news_app.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.example.news_app.model.LocalServerNewsItem
import com.bumptech.glide.Glide
import com.example.news_app.R

class DetailedNewsViewActivity : AppCompatActivity() {

    lateinit var article : LocalServerNewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_news_view)
       article = intent.getSerializableExtra("article") as LocalServerNewsItem

        val heading = findViewById<TextView>(R.id.tvNewsHeadline)
        val subHeading = findViewById<TextView>(R.id.tvNewsSubHeadline)
        val author = findViewById<TextView>(R.id.tvNewsAuthor)
        val location = findViewById<TextView>(R.id.tvNewsLocation)
        val date = findViewById<TextView>(R.id.tvNewsDate)
        val content = findViewById<TextView>(R.id.tvNewsContent)
        val image1 = findViewById<ImageView>(R.id.ivImage1)
        val image2 = findViewById<ImageView>(R.id.ivImage2)

        val type = Typeface.createFromAsset(assets,"sanitrixiesans.ttf")

        heading.text = article.mainheading
        subHeading.text = article.subheading1
        author.text = article.author
        location.text = article.region
        date.text = article.dateofnews
        content.text = article.content
        Glide.with(this).load(article.image1).into(image1)
        Glide.with(this).load(article.image2).into(image2)

        heading.typeface = type
        subHeading.typeface = type
        author.typeface = type
        location.typeface = type
        date.typeface = type
        content.typeface = type
    }
}