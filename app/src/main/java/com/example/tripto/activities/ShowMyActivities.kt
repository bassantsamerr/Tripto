package com.example.tripto.activities

import ActivityAdapter
import ActivityModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R

class ShowMyActivities : AppCompatActivity() {
    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var activityList: List<ActivityModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_activities)

        // Initialize your activity list
        activityList = getMyActivities()

        // Set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.showmyActivityRv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        activityAdapter = ActivityAdapter(activityList)
        recyclerView.adapter = activityAdapter
    }

    // Example function to get the list of activities
    private fun getMyActivities(): List<ActivityModel> {
        // Replace this with your actual logic to fetch the activities
        val activity1 = ActivityModel(
            name = "Activity 1",
            description = "Description of Activity 1",
            location = "Location 1",
            image = "Image URL 1",
            place_id = 1,
            Phone = "123456789",
            price = 100,
            Time = 2,
            socialmedia = "Social Media 1"
        )
        val activity2 = ActivityModel(
            name = "Activity 2",
            description = "Description of Activity 2",
            location = "Location 2",
            image = "Image URL 2",
            place_id = 2,
            Phone = "987654321",
            price = 200,
            Time = 3,
            socialmedia = "Social Media 2"
        )
        val activity3 = ActivityModel(
            name = "Activity 3",
            description = "Description of Activity 3",
            location = "Location 3",
            image = "Image URL 3",
            place_id = 3,
            Phone = "456123789",
            price = 150,
            Time = 2,
            socialmedia = "Social Media 3"
        )

        return listOf(activity1, activity2, activity3)
    }
}