package com.technado.mvvmkotlin.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.technado.mvvmkotlin.R

class Helper {
    companion object {

        fun loadImage(imageUrl: String, imageView: ImageView) {
            Picasso.get().load(imageUrl).placeholder(R.drawable.animated).into(imageView)
        }

    }
}