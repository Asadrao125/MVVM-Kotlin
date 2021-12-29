package com.technado.mvvmkotlin.retrofit

import com.technado.mvvmkotlin.model.PhotosModel
import com.technado.mvvmkotlin.model.PostRequestModel
import com.technado.mvvmkotlin.model.PostResponseModel
import com.technado.mvvmkotlin.utils.Const.Companion.PHOTOS
import com.technado.mvvmkotlin.utils.Const.Companion.POSTS
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST(POSTS)
    fun savePost(@Body params: PostRequestModel): Call<PostResponseModel>

    @GET(POSTS)
    fun getPosts(): Call<List<PostResponseModel>>

    @GET(PHOTOS)
    fun getPhotos(): Call<List<PhotosModel>>

}