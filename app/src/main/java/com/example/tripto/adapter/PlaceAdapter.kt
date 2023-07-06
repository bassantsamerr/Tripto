package com.example.tripto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.PlaceItemBinding
import com.example.tripto.model.NearbyPlaceModel

class PlaceAdapter (private val placeModel: List<NearbyPlaceModel>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>(){

    var onItemClick :  ((NearbyPlaceModel)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.apply {
            placePoster.load(placeModel[position].image)
            placeName.text=placeModel[position].placeName
            placeLocation.text=placeModel[position].address
            placeRating.text=placeModel[position].rating.toString()
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(placeModel[position])

        }
    }

    override fun getItemCount() =placeModel.size

    inner class PlaceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = PlaceItemBinding.bind(itemView)

    }

}