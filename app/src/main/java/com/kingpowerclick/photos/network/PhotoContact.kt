package com.kingpowerclick.photos.network

import retrofit2.Call
import retrofit2.http.GET

interface PhotoContact {
    @GET("albums/1/photos/")
    fun listPhoto(): Call<List<Photo>>
}