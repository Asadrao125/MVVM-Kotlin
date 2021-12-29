package com.technado.mvvmkotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.technado.mvvmkotlin.R
import com.technado.mvvmkotlin.adapter.PostAdapter
import com.technado.mvvmkotlin.databinding.MainActivityBinding
import com.technado.mvvmkotlin.model.PostRequestModel
import com.technado.mvvmkotlin.model.PostResponseModel
import com.technado.mvvmkotlin.utils.Dialog_CustomProgress
import com.technado.mvvmkotlin.utils.SharedPref
import com.technado.mvvmkotlin.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    var binding: MainActivityBinding? = null
    lateinit var viewModel: PostViewModel
    lateinit var progressDialog: Dialog_CustomProgress
    //lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        progressDialog = Dialog_CustomProgress(this)
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        //sharedPref = SharedPref(this)

        binding?.btnPictures?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ImageViewActivity::class.java)
            startActivity(intent)
        })

        binding?.btnSave?.setOnClickListener(View.OnClickListener {
            val title2 = binding?.edtTitle?.text.toString()
            val body2 = binding?.edtBody?.toString()

            if (title2.isEmpty()) {
                Toast.makeText(applicationContext, "Title Required", Toast.LENGTH_SHORT).show()
            } else if (body2!!.isEmpty()) {
                Toast.makeText(applicationContext, "Body Required", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog.showProgressDialog()
                viewModel.getCreateNewUserObserver().observe(this, Observer<PostResponseModel?> {
                    if (it != null) {
                        progressDialog.dismissProgressDialog()
                        binding?.edtTitle?.text?.clear()
                        binding?.edtBody?.text?.clear()
                    }
                })

                val user = PostRequestModel(1, title2, body2)
                viewModel.createNewUser(user)
            }
        })

        binding?.btnGetPosts?.setOnClickListener(View.OnClickListener {
            progressDialog.showProgressDialog()
            viewModel.getAllPosts().observe(this, Observer<List<PostResponseModel>?> {
                if (it != null) {
                    progressDialog.dismissProgressDialog()
                    binding?.recyclerView?.adapter = PostAdapter(applicationContext, it)
                }
            })
            viewModel.getAllPosts()
        })

        binding?.btnPhotos?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PhotosActivity::class.java)
            startActivity(intent)
        })
    }
}