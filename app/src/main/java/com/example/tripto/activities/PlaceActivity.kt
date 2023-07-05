package com.example.tripto.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.model.Activity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlaceActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
//        recyclerView = findViewById(R.id.ActivityRV)
//        val place = intent.getParcelableExtra<NearbyPlaceModel>("nearbyplacemodel")
//        if(place!=null){
//            val text1 : TextView = findViewById(R.id.PlaceName)
//            text1.setText(place.placeName)
//        }
//        recyclerView.adapter = place?.let { ActivityAdapter(it) }
        val fabButton: FloatingActionButton = findViewById(R.id.fab)
        fabButton.setOnClickListener {
            val dialog: DialogFragment = FullscreenDialog.newInstance()
            (dialog as FullscreenDialog).setCallback(object : FullscreenDialog.Callback {
                override fun onActionClick(activitymodel: Activity) {
                    Toast.makeText(this@PlaceActivity, activitymodel.name, Toast.LENGTH_SHORT).show()
                }
            })
            dialog.show(supportFragmentManager, "tag")
        }

    }
}