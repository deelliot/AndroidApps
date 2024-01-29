package com.delliott.flickrbrowser.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.delliott.flickrbrowser.R
import com.delliott.flickrbrowser.domain.PhotoModel
import com.squareup.picasso.Picasso


const val IMAGE_SIZE = 400
class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var image: ImageView = view.findViewById(R.id.imageView)
    fun bind(photo: PhotoModel) {
        Picasso.get()
            .load(photo.url)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .resize(IMAGE_SIZE, IMAGE_SIZE)
            .centerCrop()
            .into(image)
    }
}
class PhotoAdapter() : RecyclerView.Adapter<PhotosViewHolder>() {
    var photosList: List<PhotoModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int = photosList.size

    fun setPhotos(newPhotos: List<PhotoModel>) {
        photosList = newPhotos
        notifyDataSetChanged()

    }

    fun getPhoto(position: Int): PhotoModel? {
        return if (photosList.isNotEmpty()) photosList[position] else null
    }
}