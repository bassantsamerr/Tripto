package com.example.tripto.activities

import ActivityModel
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.example.addActivityResponse
import com.example.tripto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                override fun onActionClick(activitymodel: ActivityModel,userid:Int) {
                    val call: Call<addActivityResponse> = service.addActivity(activitymodel,userid)
                    call.enqueue(object : Callback<addActivityResponse> {
                        override fun onResponse(call: Call<addActivityResponse>, response: Response<addActivityResponse>
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

                        override fun onFailure(call: Call<addActivityResponse>, t: Throwable) {
                            Log.d("on fail add activity ", t.toString())
                        }
                    })

                }
            })
            dialog.show(supportFragmentManager, "tag")
        }

    }
}