import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.databinding.SearchItemBinding
import com.example.tripto.model.ActivityModel
import com.example.tripto.model.PlaceModel

class ActivityAdapter(private var placeModel: PlaceModel) :
    RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlace = placeModel
        val activities = currentPlace.activities
        val activityItem = activities[position]
        holder.bind(activityItem)
    }


    override fun getItemCount(): Int {
        return placeModel.activities.size
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: ActivityModel) {
            binding.apply {
                placePoster.load(activity.imageUrl)
                placeName.text = activity.title
                placeLocation.text = activity.location
                placeRating.text = activity.ratings.toString()
                val maxLength = 80 // Maximum length of the truncated text
                if (activity.description.length > maxLength) {
                    description.text = activity.description.substring(0, maxLength)
                } else {
                    description.text = activity.description
                }
            }
        }
    }

}
