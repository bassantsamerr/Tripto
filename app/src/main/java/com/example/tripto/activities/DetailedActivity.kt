package com.example.tripto.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tripto.R
import com.example.tripto.adapter.ImageSwiperAdapter
import com.example.tripto.model.NearbyPlaceModel
import com.example.tripto.model.PlaceModel
import com.example.tripto.utils.Images
import me.relex.circleindicator.CircleIndicator3

class DetailedActivity : AppCompatActivity() {
    var imageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detailed)
        val place = intent.getParcelableExtra<NearbyPlaceModel>("nearbyplacemodel")
        if(place!=null){
            val text1 : TextView = findViewById(R.id.title)
            val text2 : TextView = findViewById(R.id.description)
            val text3 : TextView = findViewById(R.id.placeLocation)
            text1.setText(place.placeName)
            text2.setText(place.description)
            text3.setText(place.address)
        }
        addToList(place?.image.toString())
        postToList()
        val view_pager2 = findViewById<ViewPager2>(R.id.view_pager2)
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

        val showAct = findViewById<Button>(R.id.showActivities)
        showAct.setOnClickListener {
            val intent = Intent(this, PlaceActivity::class.java)
            intent.putExtra("placemodel",place)
            startActivity(intent)
        }
        }

    private fun addToList(image: String){
        imageList.add(image)
    }
    private fun postToList(){
        imageList.apply {
            addToList(Images.imageUrl0)
            addToList(Images.imageUrl1)
            addToList(Images.imageUrl2)
            addToList(Images.imageUrl3)
            addToList(Images.imageUrl4)
            addToList(Images.imageUrl5)
            addToList(Images.imageUrl6)
            addToList(Images.imageUrl7)
            addToList(Images.imageUrl8)
            addToList(Images.imageUrl9)
        }
    }

}