package com.example.tripto.activities

import ActivityAdapter
import ActivityModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.example.addActivityResponse
import com.example.tripto.R
import com.example.tripto.model.PlaceModel
import com.example.tripto.utils.RetrievingData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        recyclerView = findViewById(R.id.ActivityRV)
        val place = intent.getParcelableExtra<PlaceModel>("nearbyplacemodel")
        if(place!=null){
            val text1 : TextView = findViewById(R.id.PlaceName)
            text1.setText(place.placeName)
        }
        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val placeId = sharedPreference.getInt("PLACEID", 0)
        recyclerView.adapter = place?.let { ActivityAdapter(RetrievingData.getActivtiesForOnePlace(placeId)) }
        val fabButton: FloatingActionButton = findViewById(R.id.fab)
        fabButton.setOnClickListener {
            val dialog: DialogFragment = FullscreenDialog.newInstance()
            (dialog as FullscreenDialog).setCallback(object : FullscreenDialog.Callback {
                override fun onActionClick(activitymodel: ActivityModel,userid:Int) {
                    val call: Call<Int> = service.addActivity(activitymodel,userid)
                    call.enqueue(object : Callback<Int> {
                        override fun onResponse(call: Call<Int>, response: Response<Int>
                        ) {
                            if (response.isSuccessful) {
                                Log.d("edit api response", response.body().toString())
                                Toast.makeText(this@PlaceActivity, activitymodel.name+" added successfully.", Toast.LENGTH_SHORT).show()
                            }
                            else if (!response.isSuccessful) {
                                Toast.makeText(this@PlaceActivity, "adding failed", Toast.LENGTH_SHORT).show()
                                Log.d("fail add activity", response.toString())
                                Log.d("fail add activity", response?.errorBody()?.string().toString())
                            }

                        }

                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.d("on fail add activity ", t.toString())
                        }
                    })

                }
            })
            dialog.show(supportFragmentManager, "tag")
        }

    }
}