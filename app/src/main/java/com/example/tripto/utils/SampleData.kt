package com.example.tripto.utils

import android.annotation.SuppressLint
import android.util.Log
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


object SampleData {
    private val activities = listOf(
        ActivityModel(
            "horseRiding",
            "https://www.horseriding-hurghada-egypt.com/wp-content/uploads/2018/12/girl2horsesswimming-1600x900.png",
            "horse Riding description",
            3.0,
            "Cairo"
        )
    )
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
    var allPlacesList: ArrayList<NearbyPlaceModel>
    init {
        allPlacesList = ArrayList()
    }
    var top10List : ArrayList<NearbyPlaceModel>
    init {
        top10List = ArrayList()
    }

     fun getAllPlaces(): ArrayList<NearbyPlaceModel> {
        val call: Call<List<NearbyPlaceModel>> = service.getAllPlaces()
        //list= ArrayList()
        call.enqueue(object : Callback<List<NearbyPlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    allPlacesList.clear()
                    for(myData in response.body()!!){
                        allPlacesList.add(myData)
                    }
                    for (placeModel in allPlacesList) {
                        Log.d("SampleData", placeModel.toString())
                    }
                    allPlacesList.toList()
                    for (placeModel in allPlacesList) {
                        Log.d("Bassant", placeModel.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
            }

        })
         Log.d("satalana", allPlacesList.size.toString())
         for (placeModel in allPlacesList) {
             Log.d("Reeko", placeModel.toString())
         }
        return allPlacesList
    }
    fun getTop10Places(allPlaces:ArrayList<NearbyPlaceModel>): ArrayList<NearbyPlaceModel> {
        allPlaces.sortByDescending { it.rating }
        Log.d("top10placess", allPlaces.toString())
        Log.d("top10places", top10List.toString())
        top10List.addAll(allPlacesList)
        return top10List
    }



    val collections = listOf(
        MainModel("Recommended Places", allPlacesList),
        MainModel("Top 10",allPlacesList),
        MainModel("Tour Packages", allPlacesList),
        MainModel("All Places", allPlacesList)
    )

}