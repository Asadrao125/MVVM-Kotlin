package com.technado.mvvmkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.technado.mvvmkotlin.R

class ImageActivity : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var tvTitle: TextView
    lateinit var url: String
    lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        image = findViewById(R.id.image)
        tvTitle = findViewById(R.id.tvTitle)

        url = intent.getStringExtra("url")!!
        title = intent.getStringExtra("title")!!

        tvTitle.text = title
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(image)
    }
}