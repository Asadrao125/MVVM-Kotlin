package com.technado.mvvmkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.adapter.MyAdapter
import com.technado.mvvmkotlin.adapter.PictureAdapter
import com.technado.mvvmkotlin.databinding.ActivityImageviewBinding
import com.technado.mvvmkotlin.databinding.MainActivityBinding
import com.technado.mvvmkotlin.model.PhotosModel
import com.technado.mvvmkotlin.utils.Dialog_CustomProgress
import com.technado.mvvmkotlin.viewModel.PhotosViewModel

class ImageViewActivity : AppCompatActivity() {
    var binding: ActivityImageviewBinding? = null
    lateinit var viewModel: PhotosViewModel
    lateinit var myAdapter: MyAdapter
    lateinit var actionBar: ActionBar
    lateinit var progressDialog: Dialog_CustomProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_view)

        progressDialog = Dialog_CustomProgress(this)
        actionBar = this.supportActionBar!!
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        getPhotos()

        /*binding?.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })*/
    }

    fun getPhotos() {
        progressDialog.showProgressDialog()
        viewModel.getAllPictures().observe(this, Observer<List<PhotosModel>?> {
            Toast.makeText(this, "SIZE: " + it.size, Toast.LENGTH_SHORT).show()
            progressDialog.dismissProgressDialog()
            myAdapter = MyAdapter(applicationContext, it)
            binding?.viewPager?.adapter = myAdapter
            binding?.viewPager?.setPadding(100, 0, 100, 0)
        })
        viewModel.getAllPictures()
    }
}