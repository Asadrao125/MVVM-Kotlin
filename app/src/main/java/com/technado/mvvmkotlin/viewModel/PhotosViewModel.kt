package com.technado.mvvmkotlin.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.mvvmkotlin.model.PhotosModel
import com.technado.mvvmkotlin.retrofit.RetroInstance
import com.technado.mvvmkotlin.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class PhotosViewModel(application: Application) : AndroidViewModel(application) {
    var photosList: MutableLiveData<List<PhotosModel>?>

    init {
        photosList = MutableLiveData()
    }

    fun getAllPictures(): MutableLiveData<List<PhotosModel>?> {
        val call =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java).getPhotos()
        call.enqueue(object : retrofit2.Callback<List<PhotosModel>> {
            override fun onResponse(
                call: Call<List<PhotosModel>>?,
                response: Response<List<PhotosModel>>?
            ) {
                photosList.postValue(response?.body())
                if (response?.code() == 200) {
                    photosList.postValue(response.body())
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<PhotosModel>>?, t: Throwable?) {
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return photosList
    }
}