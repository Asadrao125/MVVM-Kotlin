package com.technado.mvvmkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.model.PhotosModel
import com.technado.mvvmkotlin.utils.Helper
import kotlinx.android.synthetic.main.item_pictures.view.*

class MyAdapter(val context: Context, val list: List<PhotosModel>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pictures, container, false)

        val title = list[position].title
        val imageUrl = list[position].url
        val url = list[position].url

        Helper.loadImage(imageUrl, view.image)
        view.tvTitle.text = title
        view.tvUrl.text = url
        view.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "" + title, Toast.LENGTH_SHORT).show()
        })
        container.addView(view, position)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}