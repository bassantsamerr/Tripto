import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.databinding.ActivityItemBinding

class ActivityAdapter(private var activityModel: List<ActivityModel>) :
    RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityItem = activityModel[position]
        holder.bind(activityItem)
    }

    override fun getItemCount(): Int {
        return activityModel.size
    }

    inner class ViewHolder(private val activityBinding: ActivityItemBinding) :
        RecyclerView.ViewHolder(activityBinding.root) {

        fun bind(activity: ActivityModel) {
            activityBinding.apply {
                ActivityPoster.load(activity.image)
                activityName.text = activity.name
                activtiylocation.text = activity.location
                activtiyphoneNumber.text = activity.Phone
                activtiyprice.text = activity.price.toString()
                activtiytime.text = activity.Time.toString()
                status.text = "pending"
            }
        }
    }
}
