package com.example.tripto.utils

import android.annotation.SuppressLint
import android.util.Log
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object SampleData {

    private val placeModels = listOf(
        NearbyPlaceModel(
            "ahmed",
            "The Egyptian Museum In Cairo",
            "The Egyptian Museum is the oldest archaeological museum in the Middle East, and houses the largest collection of Pharaonic antiquities in the world. The museum displays an extensive collection spanning from the Predynastic Period to the Greco-Roman Era.",
            "Cairo",
            2514.2,
            "cairo",
            5,
            30.04846529,
            31.23365667

        )
    )
    private val service: ApiInterface = ApiInterface.create()
    var allPlaceslist: ArrayList<NearbyPlaceModel>
    init {
        allPlaceslist = ArrayList()
    }
    var top10Placeslist: ArrayList<NearbyPlaceModel>
    init {
        top10Placeslist = ArrayList()
    }
     fun getAllPlaces(): ArrayList<NearbyPlaceModel> {
        val call: Call<List<NearbyPlaceModel>> = service.getAllPlaces()
        //list= ArrayList()
        call.enqueue(object : Callback<List<NearbyPlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    allPlaceslist.clear()
                    for(myData in response.body()!!){
                        allPlaceslist.add(myData)
                    }
                    for (placeModel in allPlaceslist) {
                        Log.d("SampleData", placeModel.toString())
                    }
                    allPlaceslist.toList()
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
            }

        })
        return allPlaceslist
    }
    fun getTop10laces(): ArrayList<NearbyPlaceModel> {
        val call: Call<List<NearbyPlaceModel>> = service.getTop10Places()
        call.enqueue(object : Callback<List<NearbyPlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    top10Placeslist.clear()
                    for(myData in response.body()!!){
                        top10Placeslist.add(myData)
                    }
                    for (placeModel in top10Placeslist) {
                        Log.d("Top10", placeModel.toString())
                    }
                    top10Placeslist.toList()
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
            }

        })
        return top10Placeslist
    }


    val collections = listOf(
        MainModel("Recommended Places", allPlaceslist),
        MainModel("Top 10", getTop10laces()),
        MainModel("Tour Packages", allPlaceslist),
        MainModel("All Places", allPlaceslist)
    )

}