package com.example.tripto

import ActivityAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.model.PlaceModel

class PlaceActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        recyclerView = findViewById(R.id.ActivityRV)
        val place = intent.getParcelableExtra<PlaceModel>("placemodel")
        if(place!=null){
            val text1 : TextView = findViewById(R.id.PlaceName)
            text1.setText(place.title)
        }
        recyclerView.adapter = place?.let { ActivityAdapter(it) }

    }
}