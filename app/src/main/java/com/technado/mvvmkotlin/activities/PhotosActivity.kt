package com.technado.mvvmkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.adapter.PhotosAdapter
import com.technado.mvvmkotlin.databinding.DemoClass
import com.technado.mvvmkotlin.model.PhotosModel
import com.technado.mvvmkotlin.utils.Dialog_CustomProgress
import com.technado.mvvmkotlin.viewModel.PhotosViewModel

class PhotosActivity : AppCompatActivity() {
    var binding: DemoClass? = null
    lateinit var viewModel: PhotosViewModel
    lateinit var progressDialog: Dialog_CustomProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)

        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        progressDialog = Dialog_CustomProgress(this)

        getPics()
    }

    fun getPics() {
        progressDialog.showProgressDialog()
        viewModel.getAllPictures().observe(this, Observer<List<PhotosModel>?> {
            if (it != null) {
                progressDialog.dismissProgressDialog()
                binding?.recyclerView?.adapter = PhotosAdapter(applicationContext, it, this)
            }
        })
        viewModel.getAllPictures()
    }
}