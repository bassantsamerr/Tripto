package com.example.tripto.activities

import ActivityAdapter
import ActivityModel
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.utils.RetrievingData.getActivitiesOfEntrepreneur

class ShowMyActivities : AppCompatActivity() {
    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var activityList: List<ActivityModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_activities)
        val sharedPreference =getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        activityList = getActivitiesOfEntrepreneur(userid)
        // Set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.showmyActivityRv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        activityAdapter = ActivityAdapter(activityList)
        recyclerView.adapter = activityAdapter
    }
}