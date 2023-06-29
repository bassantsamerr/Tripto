package com.example.tripto.adapter
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.activities.DetailedActivity
import com.example.tripto.R
import com.example.tripto.databinding.ParentItemBinding
import com.example.tripto.model.MainModel
import com.example.tripto.model.NearbyPlaceModel

class MainAdapter(private val collection : List<MainModel>) : RecyclerView.Adapter<MainAdapter.CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.parent_item,parent,false)
        return CollectionViewHolder(view)
    }
    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.binding.apply {
            val currentCollection = collection[position]
            placeGenre.text = currentCollection.title
            val placeAdapter = PlaceAdapter(currentCollection.AllPlacesModel)
            rvChild.adapter = placeAdapter
            placeAdapter.onItemClick={
                val intent= Intent(holder.itemView.context, DetailedActivity::class.java)
                intent.putExtra("nearbyplacemodel",it)
                holder.itemView.context.startActivity(intent)
            }
        }
    }
    override fun getItemCount() =collection.size
    inner class CollectionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = ParentItemBinding.bind(itemView)
    }
}



