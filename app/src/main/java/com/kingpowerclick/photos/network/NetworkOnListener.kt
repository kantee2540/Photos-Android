package com.kingpowerclick.photos.network

interface NetworkOnListener {
    fun onResponse(photos: List<Photo>?)
    fun onFailed()

}