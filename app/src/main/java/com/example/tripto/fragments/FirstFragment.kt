package com.example.tripto.fragments

import TourpackageAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.adapter.MainAdapter
import com.example.tripto.model.MainModel
import com.example.tripto.model.PlaceModel
import com.example.tripto.model.TourPackageModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.RetrievingData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment(),LocationListener {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var log: Double = 0.0
    val sharedPreference by lazy {
        requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
    }
    val userid by lazy {
        sharedPreference.getInt("ID", 0)
    }
    val nationality by lazy {
        sharedPreference.getString("COUNTRY", "")
    }
    val service: ApiInterface = ApiInterface.create()

    private lateinit var collections: List<MainModel>
    private lateinit var placesTourPackage: List<PlaceModel>
    init {
        placesTourPackage   = ArrayList()
    }
    private var tourPackage: List<TourPackageModel> = listOf(
        TourPackageModel(
            packageName = "TourPackage1",
            places = placesTourPackage
        )
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvMain)
        val recyclerViewtp: RecyclerView = view.findViewById(R.id.tourpackageRview)
        collections = listOf(
            MainModel("Recommended Places", RetrievingData.getRecommendedPlaces(userid,nationality!!)),
            MainModel("Top 10", RetrievingData.getTop10places()),
            MainModel("All Places", RetrievingData.getAllPlaces())
        )
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
//        recyclerViewtp.adapter=TourpackageAdapter(tourPackage)
//        recyclerViewtp.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = MainAdapter(collections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchLocation() {
        val task:Task<Location> = fusedLocationClient.lastLocation
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)
        !=PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener{
            if(it!=null){
                log=it.longitude
                lat=it.latitude
                Log.d("logtp",it.longitude.toString())
                Log.d("lattp",it.latitude.toString())
                val call: Call<List<PlaceModel>> = service.getTourPackage(userid, log,lat)
                call.enqueue(object:Callback<List<PlaceModel>>{
                    override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
                        if(response.isSuccessful){
                            placesTourPackage= response.body()!!
                            Log.d("placesTourPackage api response", response.body().toString())
//                            var namepackage:String=""
//
//                            for (i in placesTourPackage){
//                                namepackage=i.placeName+" "
//                            }
//                            tourPackage = listOf( TourPackageModel(
//                                packageName = namepackage,
//                                places = placesTourPackage
//                            ))
                        }
                        if(!response.isSuccessful){
                            Log.d("placesTourPackage api", response.toString())
                            Log.d("placesTourPackage api", response?.errorBody()?.string().toString())
                        }

                    }

                    override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                        Log.d("placesTourPackage  edit ", t.toString())

                    }
                })
            }
        }

    }

    override fun onLocationChanged(p0: Location) {
        TODO("Not yet implemented")
    }

}
