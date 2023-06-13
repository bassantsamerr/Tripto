package com.example.tripto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.tripto.model.PlaceModel

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val place = intent.getParcelableExtra<PlaceModel>("placemodel")
        if(place!=null){
            val text1 :TextView= findViewById(R.id.title)
            val image :ImageView= findViewById(R.id.poster)
            val text2 :TextView= findViewById(R.id.description)
            text1.setText(place.title)
            text2.setText(place.description)
            image.load(place.imageUrl)

        }
    }
}