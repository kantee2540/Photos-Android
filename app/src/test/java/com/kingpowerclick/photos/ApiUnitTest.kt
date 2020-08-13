package com.kingpowerclick.photos

import com.kingpowerclick.photos.network.Photo
import com.kingpowerclick.photos.network.PhotoContact
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUnitTest {

    @Test
    fun onSuccessTest(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PhotoContact::class.java)
        val call: Call<List<Photo>> = service.listPhoto()
        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val itemCount = response.body()!!.size
                Assert.assertEquals(200, response.code())
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }
        })
    }
}