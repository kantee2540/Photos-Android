package com.kingpowerclick.photos

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.kingpowerclick.photos.network.Photo
import kotlinx.android.synthetic.main.photo_list_item.view.*

class PhotoAdapter(private val photoSet: List<Photo>?, private val context: Context)
    : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun getItemCount(): Int {
        return photoSet!!.count()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val title = this.photoSet!![position].title
        val imageUrl = GlideUrl(this.photoSet[position].thumbnailUrl, LazyHeaders.Builder()
            .addHeader("User-Agent", "your-user-agent")
            .build())

        holder.titleItem.text = title
        Glide.with(context)
            .load(imageUrl)
            .into(holder.photoItem)

        holder.photoItemView.setOnClickListener {
            val intent = Intent(context, PreviewActivity::class.java)
            intent.putExtra("imageUrl",this.photoSet[position].thumbnailUrl)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_list_item, parent, false))
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleItem = itemView.title_item
        val photoItem = itemView.image_item
        val photoItemView = itemView.photo_item_view
    }
}