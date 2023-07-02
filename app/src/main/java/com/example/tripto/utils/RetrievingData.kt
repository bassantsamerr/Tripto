package com.example.tripto.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RetrievingData {

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
    var favPlacesList: ArrayList<NearbyPlaceModel>
    init {
        favPlacesList = ArrayList()
    }
    var favPlaces: ArrayList<NearbyPlaceModel>
    init {
        favPlaces = ArrayList()
    }
    var searchHistoryPlaces: ArrayList<NearbyPlaceModel>
    init {
        searchHistoryPlaces = ArrayList()
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
    fun getfavPlaces(context: Context): ArrayList<NearbyPlaceModel> {
        val sharedPreference =context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        val callGetFavPlacesId: Call<List<NearbyPlaceModel>> = service.getFavPlaces(userid)
        callGetFavPlacesId.enqueue(object : Callback<List<NearbyPlaceModel>> {
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    favPlaces.clear()
                    for(myData in response.body()!!){
                        favPlaces.add(myData)
                    }
                    for (placeModel in favPlaces) {
                        Log.d("FavData", placeModel.toString())
                    }
                    favPlaces.toList()
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return favPlaces
    }
    fun getSearchHistoryPlaces(context: Context): ArrayList<NearbyPlaceModel> {
        val sharedPreference =context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        val callGetFavPlacesId: Call<List<NearbyPlaceModel>> = service.getSearchHistoryForUser(userid)
        callGetFavPlacesId.enqueue(object : Callback<List<NearbyPlaceModel>> {
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    searchHistoryPlaces.clear()
                    for(myData in response.body()!!){
                        searchHistoryPlaces.add(myData)
                    }
                    for (placeModel in searchHistoryPlaces) {
                        Log.d("FavData", placeModel.toString())
                    }
                    searchHistoryPlaces.toList()
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return searchHistoryPlaces
    }


    val collections = listOf(
        MainModel("Recommended Places", getAllPlaces()),
        MainModel("Top 10", getTop10laces()),
        MainModel("Tour Packages", getAllPlaces()),
        MainModel("All Places", getAllPlaces())
    )

}