package com.technado.mvvmkotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.mvvmkotlin.model.PostRequestModel
import com.technado.mvvmkotlin.model.PostResponseModel
import com.technado.mvvmkotlin.retrofit.RetroInstance
import com.technado.mvvmkotlin.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(application: Application) : AndroidViewModel(application) {
    var createNewUserLiveData: MutableLiveData<PostResponseModel?>
    var posts: MutableLiveData<List<PostResponseModel>?>

    init {
        createNewUserLiveData = MutableLiveData()
        posts = MutableLiveData()
    }

    fun getCreateNewUserObserver(): MutableLiveData<PostResponseModel?> {
        return createNewUserLiveData
    }

    fun createNewUser(user: PostRequestModel) {
        val retroService =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroService.savePost(user)
        call.enqueue(object : Callback<PostResponseModel> {
            override fun onResponse(
                call: Call<PostResponseModel>?,
                response: Response<PostResponseModel>?
            ) {
                if (response!!.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                    Toast.makeText(
                        getApplication(),
                        "Successfully Saved " + response.body().title,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        getApplication(), "Oops...!! Something went wrong", Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<PostResponseModel>?, t: Throwable?) {
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun getAllPosts(): MutableLiveData<List<PostResponseModel>?> {
        val retroService =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroService.getPosts()
        call.enqueue(object : Callback<List<PostResponseModel>> {
            override fun onResponse(
                call: Call<List<PostResponseModel>>?,
                response: Response<List<PostResponseModel>>?
            ) {
                if (response!!.isSuccessful) {
                    posts.postValue(response.body())
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<PostResponseModel>>?, t: Throwable?) {
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return posts
    }
}