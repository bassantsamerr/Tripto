package com.example.tripto.utils

import ActivityModel
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RetrievingData {
    private val service: ApiInterface = ApiInterface.create()
    var allPlaceslist: ArrayList<PlaceModel>
    init {
        allPlaceslist = ArrayList()
    }
    var top10Placeslist: ArrayList<PlaceModel>
    init {
        top10Placeslist = ArrayList()
    }
    var favPlacesList: ArrayList<PlaceModel>
    init {
        favPlacesList = ArrayList()
    }
    var favPlaces: ArrayList<PlaceModel>
    init {
        favPlaces = ArrayList()
    }
    var searchHistoryPlaces: ArrayList<PlaceModel>
    init {
        searchHistoryPlaces = ArrayList()
    }
    var recommendedPlaces: ArrayList<PlaceModel>
    init {
        recommendedPlaces = ArrayList()
    }
    var ActivitiesForOnePlace: ArrayList<ActivityModel>
    init {
        ActivitiesForOnePlace = ArrayList()
    }
    var ActivitiesOfEntrepreneur: ArrayList<ActivityModel>
    init {
        ActivitiesOfEntrepreneur = ArrayList()
    }
     fun getAllPlaces(): ArrayList<PlaceModel> {
        val call: Call<List<PlaceModel>> = service.getAllPlaces()
        //list= ArrayList()
        call.enqueue(object : Callback<List<PlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
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

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
            }

        })
        return allPlaceslist
    }

    fun getTop10places(): ArrayList<PlaceModel> {
        val call: Call<List<PlaceModel>> = service.getTop10Places()
        call.enqueue(object : Callback<List<PlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
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

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
            }

        })
        return top10Placeslist
    }
    fun getfavPlaces(context: Context): ArrayList<PlaceModel> {
        val sharedPreference =context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        val callGetFavPlacesId: Call<List<PlaceModel>> = service.getFavPlaces(userid)
        callGetFavPlacesId.enqueue(object : Callback<List<PlaceModel>> {
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
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

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return favPlaces
    }
    fun getSearchHistoryPlaces(context: Context): ArrayList<PlaceModel> {
        val sharedPreference =context.getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        val callGetFavPlacesId: Call<List<PlaceModel>> = service.getSearchHistoryForUser(userid)
        callGetFavPlacesId.enqueue(object : Callback<List<PlaceModel>> {
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
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

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                Log.d("on fail get fav places ids ", t.toString())
            }

        })
        return searchHistoryPlaces
    }
    fun getRecommendedPlaces(userid:Int,Nationality:String): ArrayList<PlaceModel> {
        val call: Call<List<PlaceModel>> = service.getRecommendedPlaces(userid,Nationality)
        call.enqueue(object : Callback<List<PlaceModel>> {
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
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

            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
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
                        Log.d("getActivtiesForOnePlace", placeModel.toString())
                    }
                    ActivitiesForOnePlace.toList()
                }
                else{
                    Log.d("getActivtiesForOnePlace api", response.toString())
                    Log.d("getActivtiesForOnePlace api", response?.errorBody()?.string().toString())
                }
            }

            override fun onFailure(call: Call<List<ActivityModel>>, t: Throwable) {
                Log.d("on fail getActivtiesForOnePlace ", t.toString())
            }

        })
        return ActivitiesForOnePlace
    }
    fun getActivitiesOfEntrepreneur(userid:Int): ArrayList<ActivityModel> {
        val call: Call<List<ActivityModel>> = service.getActivitiesOfEntrepreneur(userid)
        call.enqueue(object : Callback<List<ActivityModel>> {
            override fun onResponse(call: Call<List<ActivityModel>>, response: Response<List<ActivityModel>>) {
                if(response.isSuccessful){
                    ActivitiesOfEntrepreneur.clear()
                    for(myData in response.body()!!){
                        ActivitiesOfEntrepreneur.add(myData)
                    }
                    for (placeModel in ActivitiesOfEntrepreneur) {
                        Log.d("ActivitiesOfEntrepreneur", placeModel.toString())
                    }
                    ActivitiesOfEntrepreneur.toList()
                }
                else{
                    Log.d("ActivitiesOfEntrepreneur api", response.toString())
                    Log.d("ActivitiesOfEntrepreneur api", response?.errorBody()?.string().toString())
                }
            }

            override fun onFailure(call: Call<List<ActivityModel>>, t: Throwable) {
                Log.d("on fail ActivitiesOfEntrepreneur ", t.toString())
            }
        })
        return ActivitiesOfEntrepreneur
    }



}