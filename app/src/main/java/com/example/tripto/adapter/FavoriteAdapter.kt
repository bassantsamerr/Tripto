package com.example.tripto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.PlaceItemBinding
import com.example.tripto.model.NearbyPlaceModel
import com.google.android.material.imageview.ShapeableImageView

class FavoriteAdapter(private val placeModel: List<NearbyPlaceModel>) : RecyclerView.Adapter<FavoriteAdapter.PlaceViewHolder>(), Filterable {

    private var mListener: onItemClickListener? = null

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.apply {
            placePoster.load(placeModel[position].image)
            placeName.text = placeModel[position].placeName
            placeLocation.text = placeModel[position].address
            placeRating.text = placeModel[position].rating.toString()
        }
        holder.itemView.setOnClickListener {
            mListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return placeModel.size
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PlaceItemBinding.bind(itemView)
    }

    override fun getFilter(): Filter {
        // Implement the filter functionality if needed
        TODO()
    }
}
