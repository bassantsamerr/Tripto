package com.example.tripto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.PlaceItemBinding
import com.example.tripto.model.PlaceModel

class PlaceAdapter (private val placeModel: List<PlaceModel>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.apply {
            placePoster.load(placeModel[position].imageUrl)
            placeName.text=placeModel[position].title
            placeLocation.text=placeModel[position].location
            placeRating.text=placeModel[position].ratings.toString()
        }
    }

    override fun getItemCount() =placeModel.size

    inner class PlaceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = PlaceItemBinding.bind(itemView)

    }

}