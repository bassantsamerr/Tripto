package com.example.tripto.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripto.adapter.PlaceAdapter
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


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
    var list: ArrayList<NearbyPlaceModel>
    init {
        list = ArrayList()
    }
     fun getPlaceModels(): ArrayList<NearbyPlaceModel> {
        val call: Call<List<NearbyPlaceModel>> = service.getAllPlaces()
        //list= ArrayList()
        call.enqueue(object : Callback<List<NearbyPlaceModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<NearbyPlaceModel>>, response: Response<List<NearbyPlaceModel>>) {
                if(response.isSuccessful){
                    list.clear()
                    for(myData in response.body()!!){
                        list.add(myData)
                    }
                    for (placeModel in list) {
                        Log.d("SampleData", placeModel.toString())
                    }
                    list.toList()
                    for (placeModel in list) {
                        Log.d("Bassant", placeModel.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<NearbyPlaceModel>>, t: Throwable) {
            }

        })
         Log.d("satalana", list.size.toString())
         for (placeModel in list) {
             Log.d("Reeko", placeModel.toString())
         }
        return list
    }


    val collections = listOf(
        MainModel("Recommended Places", list),
        MainModel("Top 10", list),
        MainModel("Tour Packages", list),
        MainModel("All Places", list)
    )

}