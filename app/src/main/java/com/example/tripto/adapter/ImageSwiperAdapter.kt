package com.example.tripto.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R

class ImageSwiperAdapter(private var images: List<String>) :
    RecyclerView.Adapter<ImageSwiperAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.poster)

        init {
            itemImage.setOnTouchListener { _, event ->
                // Prevent swiping the entire page by consuming touch events
                return@setOnTouchListener event.actionMasked != MotionEvent.ACTION_UP
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image, parent, false)
        return Pager2ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.itemImage.load(imageUrl) {
            // Optional: Add any additional image loading configuration here
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

}