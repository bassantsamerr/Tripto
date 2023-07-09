package com.example.tripto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.tripto.R
import com.example.tripto.adapter.ImageSwiperAdapter
import com.example.tripto.fragments.FirstFragment
import com.google.android.gms.location.DetectedActivity
import me.relex.circleindicator.CircleIndicator3

class DetailedTourPackage : AppCompatActivity() {
    var imageList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_tour_package)
        if(FirstFragment.placePackage!=null){
            val text1 : TextView = findViewById(R.id.title)
            val text2 : TextView = findViewById(R.id.description)
            val text3 : TextView = findViewById(R.id.placeLocation)
            val text4 : TextView = findViewById(R.id.estimatedBudget)
            val text5 : TextView = findViewById(R.id.estimatedTime)
            text1.setText(FirstFragment.placePackage!!.placeName)
            text2.setText(FirstFragment.placePackage!!.description)
            text3.setText(FirstFragment.placePackage!!.address)
            text5.setText(FirstFragment.placePackage!!.estimatedDuration.toString()+"Hours")
            if(FirstFragment.placePackage!!.ticketPrice.toString()=="0.0"){text4.text="Free"}
            else {
                text4.text = FirstFragment.placePackage!!.ticketPrice.toString() + "L.E"
            }

            val view_pager2 = findViewById<ViewPager2>(R.id.view_pager2)
            imageList.add(FirstFragment.placePackage!!.image.toString())
            view_pager2.adapter= ImageSwiperAdapter(imageList)
            view_pager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
            val indicator = findViewById<CircleIndicator3>(R.id.indicator)
            indicator.setViewPager(view_pager2)
            val downWardBox: CheckBox = findViewById(R.id.downMenu)
            val descriptionTextView: TextView = findViewById(R.id.description)
            downWardBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    descriptionTextView.visibility = View.VISIBLE
                } else {
                    descriptionTextView.visibility = View.GONE
                }
            }
        }
         fun addToList(image: String){
            imageList.add(image)
        }
    }
}