package com.example.tripto

import com.example.tripto.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

        @POST("/addUser")
        fun addUser(@Body user: UserModel): Call<ResponseBody>
        @GET("/nearbyPlaces")
        fun get_nearby_places(@Body latitude: Float, longitude: Float, n:Int): Call<ResponseBody>

        @GET("/nearestPlace")
        fun get_nearest_places(@Query("latitude") latitude: Float, @Query("longitude") longitude: Float): Call<ResponseBody>


    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}