package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tripto.R
import com.example.tripto.adapter.ImageSwiperAdapter
import com.example.tripto.model.DeleteResponse
import com.example.tripto.model.PlaceModel
import com.example.tripto.model.PlaceToUserModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.Images
import me.relex.circleindicator.CircleIndicator3
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailedActivity : AppCompatActivity() {
    var imageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service: ApiInterface = ApiInterface.create()
        setContentView(R.layout.activity_detailed)
        val place = intent.getParcelableExtra<PlaceModel>("nearbyplacemodel")
        val sharedPreference =getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid=sharedPreference.getInt("ID",0)
        val editor = sharedPreference.edit()
        editor.putInt("PLACEID", place?.id!!)
        editor.apply()
        val placeid=place?.id
        val addFav = findViewById<ImageView>(R.id.cdHeart)
        var isFav=false
        if(place!=null){
            val text1 : TextView = findViewById(R.id.title)
            val text2 : TextView = findViewById(R.id.description)
            val text3 : TextView = findViewById(R.id.placeLocation)
            val text4 : TextView = findViewById(R.id.price)
            text1.setText(place.placeName)
            text2.setText(place.description)
            text3.setText(place.address)
            if(place.ticketPrice.toString()=="0.0"){text4.text="Free"}
            else {
                text4.text = place.ticketPrice.toString() + "L.E"
            }
            val call: Call<List<Int>> = service.getFavPlacesIDs(userid)
            call.enqueue(object : Callback<List<Int>>{
                override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                    if(response.isSuccessful) {
                        val favPlaces = response.body()
                        if (favPlaces != null) {
                            if (favPlaces.contains(placeid!!)) {
                                addFav.setImageResource(R.drawable.heart_filled)
                                isFav = true
                            }
                        }
                        Log.d("fav places", favPlaces.toString())
                    }
                else if(!response.isSuccessful)
                {
                    Log.d("fail get fav places", response.toString())
                    Log.d("fail get fav places", response.errorBody()?.string().toString())
                }
                }
                override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                    Log.d("on fail get fav places ", t.toString())
                }

            })
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
            intent.putExtra("nearbyplacemodel",place)
            startActivity(intent)
        }


        addFav.setOnClickListener{
            val placetouser=PlaceToUserModel(placeid!!,userid)
            val callAdd: Call<ResponseBody> = service.addFavPlace(placetouser)
            val callDelete: Call<DeleteResponse> = service.deleteFavPlace(placeid,placeid,userid)
            Log.d("call add",callAdd.toString())
            Log.d("call delete",callDelete.toString())
            if(isFav==false){
                callAdd.enqueue(object : Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@DetailedActivity, "place added to your favorites", Toast.LENGTH_SHORT).show()
                            Log.d("add fav api response", response.body().toString())
                            addFav.setImageResource(R.drawable.heart_filled)
                            isFav=true
                            val call: Call<List<Int>> = service.getFavPlacesIDs(userid)
                            call.enqueue(object : Callback<List<Int>>{
                                override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                                    val favPlaces=response.body()
                                    if (favPlaces != null) {
                                        if(favPlaces.contains(placeid!!)){
                                            addFav.setImageResource(R.drawable.heart_filled)
                                            isFav=true
                                        }
                                    }
                                    Log.d("fav places",favPlaces.toString())
                                }

                                override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                                    Log.d("on fail get fav places ", t.toString())
                                }

                            })
                        }
                        else if (!response.isSuccessful) {
                            Toast.makeText(this@DetailedActivity, "add to favorites failed", Toast.LENGTH_SHORT).show()
                            Log.d("add fav api", response.toString())
                            Log.d("add fav api", response?.errorBody()?.string().toString())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("on fail  add fav ", t.toString())
                    }

                })
            }
            else{
                callDelete.enqueue(object : Callback<DeleteResponse>{
                    override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@DetailedActivity, "place deleted from your favorites", Toast.LENGTH_SHORT).show()
                            Log.d("delete fav api response", response.body().toString())
                            addFav.setImageResource(R.drawable.heart_outlined)
                            isFav=false
                            val call: Call<List<Int>> = service.getFavPlacesIDs(userid)
                            call.enqueue(object : Callback<List<Int>>{
                                override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                                    val favPlaces=response.body()
                                    if (favPlaces != null) {
                                        if(favPlaces.contains(placeid!!)){
                                            addFav.setImageResource(R.drawable.heart_filled)
                                            isFav=true
                                        }
                                    }
                                    Log.d("fav places",favPlaces.toString())
                                }

                                override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                                    Log.d("on fail get fav places ", t.toString())
                                }

                            })
                        }
                        else if (!response.isSuccessful) {
                            Toast.makeText(this@DetailedActivity, "add to favorites failed", Toast.LENGTH_SHORT).show()
                            Log.d("delete fav api", response.toString())
                            Log.d("delete fav api", response?.errorBody()?.string().toString())
                        }
                    }

                    override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                        Log.d("on fail  delete fav ", t.toString())
                    }

                })
            }

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