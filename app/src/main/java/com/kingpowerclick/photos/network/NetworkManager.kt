package com.kingpowerclick.photos.network

import com.kingpowerclick.photos.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager{

    fun callApi(callback: NetworkOnListener){
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PhotoContact::class.java)
        val call: Call<List<Photo>> = service.listPhoto()
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.code() == 200) {
                    callback.onResponse(response.body())
                }else{
                    callback.onFailed()
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                callback.onFailed()
            }
        })

    }
}