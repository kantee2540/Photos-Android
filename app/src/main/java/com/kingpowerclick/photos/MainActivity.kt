package com.kingpowerclick.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kingpowerclick.photos.network.NetworkManager
import com.kingpowerclick.photos.network.NetworkOnListener
import com.kingpowerclick.photos.network.Photo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NetworkOnListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetworkManager().callApi(this)
        
    }

    override fun onResponse(photos: List<Photo>?) {
        this.runOnUiThread {
            photo_recyclerview.adapter = PhotoAdapter(photoSet = photos, context = this)
            photo_recyclerview.layoutManager = GridLayoutManager(this, 2)
            photo_recyclerview.addItemDecoration(GridSpacingItemDecoration(80, 80, 80, 80))
        }
    }

    override fun onFailed() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }
}