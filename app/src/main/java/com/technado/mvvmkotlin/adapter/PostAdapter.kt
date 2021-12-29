package com.technado.mvvmkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.model.PostResponseModel

class PostAdapter(var context: Context, var list: List<PostResponseModel>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title
        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}