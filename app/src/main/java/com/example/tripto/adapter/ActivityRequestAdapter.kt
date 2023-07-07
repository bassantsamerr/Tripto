import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.activities.ShowAllActivityRequestsAdmin
import com.example.tripto.databinding.ActivityRequestItemBinding
import com.example.tripto.model.DeleteResponse
import com.example.tripto.model.PlaceModel
import com.example.tripto.model.admin_AvtivityResponseModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.RetrievingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ActivityRequestAdapter(private var activityList: List<ActivityModel>) :
    RecyclerView.Adapter<ActivityRequestAdapter.ViewHolder>() {
    val service: ApiInterface = ApiInterface.create()

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

                accept.setOnClickListener{
                    val admin_AvtivityResponseModel= admin_AvtivityResponseModel(activity.id,true)
                    val call: Call<DeleteResponse> = service.admin_AvtivityResponse(admin_AvtivityResponseModel)
                    call.enqueue(object :  Callback<DeleteResponse>{
                        override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                            if (response.isSuccessful) {
                                Toast.makeText(itemView.context, "Activity Approved Successfully", Toast.LENGTH_SHORT).show()

                                Log.d("admin_AvtivityResponse api response", response.body().toString())
                                ShowAllActivityRequestsAdmin.activityAdapter.updateData(RetrievingData.getPendingActivitiesAdmin())

                            }
                            else if (!response.isSuccessful) {
                                Toast.makeText(itemView.context, "Approving failed", Toast.LENGTH_SHORT).show()
                                Log.d("fail admin_AvtivityResponse api", response.toString())
                                Log.d("fail admin_AvtivityResponse api", response?.errorBody()?.string().toString())
                            }
                        }

                        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                            Log.d("on fail  admin_AvtivityResponse ", t.toString())
                        }
                    })
                }
                reject.setOnClickListener{
                    val admin_AvtivityResponseModel= admin_AvtivityResponseModel(activity.id,false)
                    val call: Call<DeleteResponse> = service.admin_AvtivityResponse(admin_AvtivityResponseModel)
                    call.enqueue(object :  Callback<DeleteResponse>{
                        override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                            if (response.isSuccessful) {
                                Toast.makeText(itemView.context, "Activity Rejected Successfully", Toast.LENGTH_SHORT).show()
                                Log.d("admin_AvtivityResponse api response", response.body().toString())
                                ShowAllActivityRequestsAdmin.activityAdapter.updateData(RetrievingData.getPendingActivitiesAdmin())

                            }
                            else if (!response.isSuccessful) {
                                Toast.makeText(itemView.context, "Rejecting failed", Toast.LENGTH_SHORT).show()
                                Log.d("fail admin_AvtivityResponse api", response.toString())
                                Log.d("fail admin_AvtivityResponse api", response?.errorBody()?.string().toString())
                            }
                        }

                        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                            Log.d("on fail  admin_AvtivityResponse ", t.toString())
                        }
                    })
                }

            }
        }
    }
    fun updateData(newData: ArrayList<ActivityModel>) {
        activityList = newData
        notifyDataSetChanged()
    }
}

