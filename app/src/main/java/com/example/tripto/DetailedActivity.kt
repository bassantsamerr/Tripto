package com.example.tripto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.tripto.adapter.ImageSwiperAdapter
import com.example.tripto.model.PlaceModel
import com.example.tripto.utils.Images
import me.relex.circleindicator.CircleIndicator3

private var imageList = mutableListOf<String>()

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val place = intent.getParcelableExtra<PlaceModel>("placemodel")
        if(place!=null){
            val text1 :TextView= findViewById(R.id.title)
            val text2 :TextView= findViewById(R.id.description)
            text1.setText(place.title)
            text2.setText(place.description)
        }
        postToList()
        val view_pager2 = findViewById<ViewPager2>(R.id.view_pager2)
        view_pager2.adapter= ImageSwiperAdapter(imageList)
        view_pager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)


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