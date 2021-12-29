package com.technado.mvvmkotlin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.activities.ImageActivity
import com.technado.mvvmkotlin.model.PhotosModel
import androidx.core.util.Pair
import com.technado.mvvmkotlin.utils.Helper

class PhotosAdapter(var context: Context, var list: List<PhotosModel>, var activity: Activity) :
    RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_photos, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title

        Helper.loadImage(list[position].thumbnailUrl, holder.image)

        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "" + list[position].title, Toast.LENGTH_SHORT).show()
        })

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("url", list[position].url)
            intent.putExtra("title", list[position].title)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val transition = Pair.create<View?, String?>(holder.image, "transition")
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transition)
            activity.startActivity(intent, options.toBundle())
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var image: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            image = itemView.findViewById(R.id.image)
        }
    }
}