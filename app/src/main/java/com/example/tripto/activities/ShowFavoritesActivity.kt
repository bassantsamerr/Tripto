package com.example.tripto.activities

import SearchAdapter
import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.model.NearbyPlaceModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.SampleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowFavoritesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    var favPlacesIDs:List<Int>
    init {
        favPlacesIDs = ArrayList()
    }
    var favPlaces: ArrayList<NearbyPlaceModel>
    init {
        favPlaces = ArrayList()
    }

    val service: ApiInterface = ApiInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_favorites)
        recyclerView = findViewById(R.id.resyclerViewFav)


        // Set the RecyclerView adapter
        searchAdapter = SearchAdapter(SampleData.getfavPlaces(this))
        recyclerView.adapter = searchAdapter
    }

}