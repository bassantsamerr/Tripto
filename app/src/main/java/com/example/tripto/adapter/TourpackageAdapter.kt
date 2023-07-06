import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tripto.R
import com.example.tripto.databinding.PlaceItemBinding
import com.example.tripto.databinding.TourpackageItemBinding
import com.example.tripto.model.TourPackageModel

class TourpackageAdapter(private val tourPackageList: List<TourPackageModel>) : RecyclerView.Adapter<TourpackageAdapter.TourPackageViewHolder>() {

    var onItemClick: ((TourPackageModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourPackageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TourpackageItemBinding.inflate(inflater, parent, false)
        return TourPackageViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TourPackageViewHolder, position: Int) {
        val tourPackage = tourPackageList[position]
        holder.bind(tourPackage)
    }

    override fun getItemCount(): Int = tourPackageList.size
    inner class TourPackageViewHolder(private val binding: TourpackageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tourPackage: TourPackageModel) {
            binding.apply {
                tourPackagePoster.load(tourPackage.places[0].image)
                tourPackageName.text = tourPackage.packageName
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(tourPackage)
            }
        }
    }

}
