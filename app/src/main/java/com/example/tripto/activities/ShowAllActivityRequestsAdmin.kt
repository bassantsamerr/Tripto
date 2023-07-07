package com.example.tripto.activities

import ActivityRequestAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.utils.RetrievingData

class ShowAllActivityRequestsAdmin : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    //private lateinit var ActivityAdapter: ActivityRequestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_requests_admin)
        recyclerView = findViewById(R.id.showmyActivityRv)
        // Set the RecyclerView adapter
        activityAdapter = ActivityRequestAdapter(RetrievingData.getPendingActivitiesAdmin())
        recyclerView.adapter = activityAdapter
        activityAdapter.updateData(RetrievingData.getPendingActivitiesAdmin())

}
        companion object {
            lateinit var activityAdapter: ActivityRequestAdapter
        }


}