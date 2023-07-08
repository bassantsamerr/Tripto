package com.example.tripto.activities

import SearchAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.utils.RetrievingData

class CategoryPlacesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter:SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_places)
        recyclerView = findViewById(R.id.recyclerViewFav)
        // Set the RecyclerView adapter
        val tvCategory=findViewById<TextView>(R.id.textcategory)
        tvCategory.text="  "+CategoriesActivity.catName
        searchAdapter = SearchAdapter(ArrayList(CategoriesActivity.places))
        ArrayList(CategoriesActivity.places).clear()
        searchAdapter.onItemClick = { nearbyPlaceModel ->
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("nearbyplacemodel", nearbyPlaceModel)
            startActivity(intent)
        }
        recyclerView.adapter = searchAdapter
    }
}