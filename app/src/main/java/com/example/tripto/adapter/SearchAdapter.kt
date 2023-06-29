import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.PlaceItemBinding
import com.example.tripto.model.NearbyPlaceModel
import com.example.tripto.model.PlaceModel

class SearchAdapter(private var placeModel: ArrayList<NearbyPlaceModel>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = placeModel[position]
        holder.itemView.apply {
            val placePoster = findViewById<ImageView>(R.id.placePoster)
            val placeName = findViewById<TextView>(R.id.placeName)
            val placeLocation = findViewById<TextView>(R.id.placeLocation)
            val placeRating = findViewById<TextView>(R.id.placeRating)
            val description = findViewById<TextView>(R.id.description)

            placePoster.load(currentItem.image)
            placeName.text = currentItem.placeName
            placeLocation.text = currentItem.address
            placeRating.text = currentItem.rating.toString()

            val maxLength = 80 // Maximum length of the truncated text
            if (currentItem.description?.length !!> maxLength) {
                description.text = currentItem.description?.substring(0,80)
            }
            else {
                description.text = currentItem.description
            }
        }
    }

    override fun getItemCount(): Int {
        return placeModel.size
    }

    fun updateData(newData: ArrayList<NearbyPlaceModel>) {
        placeModel = newData
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PlaceItemBinding.bind(itemView)
    }
}
