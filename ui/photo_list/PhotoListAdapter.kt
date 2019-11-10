package com.android.insta_testproject_alfa.ui.photo_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.insta_testproject_alfa.R
import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    private var photoList = mutableListOf<PhotoItem>()

    private lateinit var imageLoader: RequestManager

    companion object {
        fun GetInstance(context: Context) {
            val imageOption = RequestOptions()
//                .placeholder(R.drawable.nyt_logo_black)
//                .fallback(R.drawable.nyt_logo_black)
                .centerCrop()
            var imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption)
        }
    }

    fun addNewPhoto(i: PhotoItem) {
        photoList.add(i)
        notifyDataSetChanged()
    }

    fun refreshPhotoList(photos: List<PhotoItem>) {
        photoList.clear()
        photoList.addAll(photos)
        notifyDataSetChanged()
    }

    fun getPhotoList(): List<PhotoItem> {
        return photoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoItem: PhotoItem = photoList[position]
        holder.bind(photoItem, position)
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_photo_item, parent, false)) {

        private var itemPhoto: ImageView = itemView.findViewById(R.id.item_photo)
        private var itemLlike: ImageView = itemView.findViewById(R.id.item_like)

        fun bind(photoItem: PhotoItem, position: Int) {

        }
    }

}