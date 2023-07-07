import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.ActivityRequestItemBinding


class ActivityRequestAdapter(private val activityList: List<ActivityModel>) :
    RecyclerView.Adapter<ActivityRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ActivityRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activityList[position]
        holder.bind(activity)
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    inner class ViewHolder(private val activityBinding: ActivityRequestItemBinding) :
        RecyclerView.ViewHolder(activityBinding.root) {

        fun bind(activity: ActivityModel) {
            activityBinding.apply {
                ActivityPoster.load(activity.image)
                activityName.text = activity.name
                activtiylocation.text = activity.location
                activtiyphoneNumber.text = activity.Phone
                activtiyprice.text = activity.price.toString()
                activtiytime.text = activity.Time.toString()

            }
        }
    }
}

