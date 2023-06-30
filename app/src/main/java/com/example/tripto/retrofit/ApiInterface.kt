package com.example.tripto.retrofit

import com.example.tripto.model.CurrentUserModel
import com.example.tripto.model.NearbyPlaceModel
import com.example.tripto.model.TokenModel
import com.example.tripto.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
        @GET("/places/all")
        fun getAllPlaces(): Call<List<NearbyPlaceModel>>
        @GET("/topRatedPlaces")
        fun getTop10Places(): Call<List<NearbyPlaceModel>>
        @POST("/addUser")
        fun addUser(@Body user: UserModel): Call<ResponseBody>
        @GET("/nearbyPlaces")
        fun get_nearby_places(@Query("latitude") latitude: Double, @Query("longitude")longitude: Double,@Query("n")n: Int): Call<List<NearbyPlaceModel>>
        @GET("/nearestPlace")
        fun get_nearest_places(@Query("latitude") latitude: Float, @Query("longitude") longitude: Float): Call<ResponseBody>
        @GET("/chatting")
        fun get_chatbot_reponse(@Query("text") text: String): Call<String>
        @FormUrlEncoded
        @POST("/token")
        fun login_for_access_token(@Field("username") username: String, @Field("password") password: String): Call<TokenModel>
        @GET("/users/me")
        fun get_current_user(@Header("Authorization") token:String ):Call<CurrentUserModel>
    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}