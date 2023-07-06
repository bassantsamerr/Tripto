package com.example.tripto.activities

import SearchAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.model.PlaceModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.RetrievingData

class ShowFavoritesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    var favPlacesIDs:List<Int>
    init {
        favPlacesIDs = ArrayList()
    }
    var favPlaces: ArrayList<PlaceModel>
    init {
        favPlaces = ArrayList()
    }

    val service: ApiInterface = ApiInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_favorites)
        recyclerView = findViewById(R.id.resyclerViewFav)


        // Set the RecyclerView adapter
        searchAdapter = SearchAdapter(RetrievingData.getfavPlaces(this))
        searchAdapter.onItemClick = { nearbyPlaceModel ->
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("nearbyplacemodel", nearbyPlaceModel)
            startActivity(intent)
        }

        recyclerView.adapter = searchAdapter

    }

}