package com.example.tripto.utils

import ActivityModel
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.tripto.activities.LoginActivty
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RetrievingData {
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
    var recommendedPlaces: ArrayList<NearbyPlaceModel>
    init {
        recommendedPlaces = ArrayList()
    }
    var ActivitiesForOnePlace: ArrayList<ActivityModel>
    init {
        ActivitiesForOnePlace = ArrayList()
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
    fun getRecommendedPlaces(userid:Int,Nationality:String): ArrayList<NearbyPlaceModel> {
//        val sharedPreference =context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
//        val userid=sharedPreference.getInt("ID",0)
        val call: Call<List<NearbyPlaceModel>> = service.getRecommendedPlaces(userid,Nationality)
        call.enqueue(object : Callback<List<NearbyPlaceModel>> {
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    recommendedPlaces.clear()
                    for(myData in response.body()!!){
                        recommendedPlaces.add(myData)
                    }
                    for (placeModel in recommendedPlaces) {
                        Log.d("RecoData", placeModel.toString())
                    }
                    recommendedPlaces.toList()
                }
                else{
                    Log.d("reco api", response.toString())
                    Log.d("reco api", response?.errorBody()?.string().toString())
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return recommendedPlaces
    }
    fun getActivtiesForOnePlace(placeid:Int): ArrayList<ActivityModel> {
        val call: Call<List<ActivityModel>> = service.getActivitesForOnePlace(placeid)
        call.enqueue(object : Callback<List<ActivityModel>> {
            override fun onResponse(call: Call<List<ActivityModel>>, response: Response<List<ActivityModel>>) {
                if(response.isSuccessful){
                    ActivitiesForOnePlace.clear()
                    for(myData in response.body()!!){
                        ActivitiesForOnePlace.add(myData)
                    }
                    for (placeModel in ActivitiesForOnePlace) {
                        Log.d("RecoData", placeModel.toString())
                    }
                    ActivitiesForOnePlace.toList()
                }
                else{
                    Log.d("reco api", response.toString())
                    Log.d("reco api", response?.errorBody()?.string().toString())
                }
            }

            override fun onFailure(call: Call<List<ActivityModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return ActivitiesForOnePlace
    }


//    val collections = listOf(
//        MainModel("Recommended Places", getRecommendedPlaces(37,"Russia")),
//        MainModel("Top 10", getTop10laces()),
//        MainModel("Tour Packages", getAllPlaces()),
//        MainModel("All Places", getAllPlaces())
//    )

}