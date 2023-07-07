package com.example.tripto.retrofit


import ActivityModel
import com.example.example.SignUpResponseModel
import com.example.example.addActivityResponse
import com.example.tripto.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
        @GET("/places/all")
        fun getAllPlaces(): Call<List<PlaceModel>>
        @GET("/topRatedPlaces")
        fun getTop10Places(): Call<List<PlaceModel>>
        @POST("/addUser")
        fun addUser(@Body user: UserModel): Call<SignUpResponseModel>
        @GET("/nearbyPlaces")
        fun get_nearby_places(@Query("latitude") latitude: Double, @Query("longitude")longitude: Double,@Query("n")n: Int): Call<List<PlaceModel>>
        @GET("/nearestPlace")
        fun get_nearest_places(@Query("latitude") latitude: Float, @Query("longitude") longitude: Float): Call<ResponseBody>
        @GET("/chatting")
        fun get_chatbot_reponse(@Query("text") text: String): Call<String>
        @FormUrlEncoded
        @POST("/token")
        fun login_for_access_token(@Field("username") username: String, @Field("password") password: String): Call<TokenModel>
        @GET("/users/me")
        fun get_current_user(@Header("Authorization") token:String ):Call<CurrentUserModel>
        @POST("/logout")
        fun logout(@Header("Authorization") token:String ):Call<DeleteResponse>
        @PUT("/editUser/{userid}")
        fun editUser(@Path("userid") userId: Int, @Body user: UserModel): Call<ResponseBody>
        @POST("/addFavplace")
        fun addFavPlace(@Body placetouser: PlaceToUserModel): Call<ResponseBody>
        @POST("/addSearchHistory")
        fun addSearchHistory(@Body placetouser: PlaceToUserSearchModel): Call<ResponseBody>
        @DELETE("/deleteFavPlace/{FavPlace_id}")
        fun deleteFavPlace(@Path("FavPlace_id") favPlaceId: Int,@Query ("placeid") placeid:Int,@Query ("userid") userid:Int): Call<DeleteResponse>
        @GET("/getFavplacesIDs")
        fun getFavPlacesIDs(@Query("userid") userid: Int):Call<List<Int>>
        @GET("/getFavplaces")
        fun getFavPlaces(@Query("userid") userid: Int):Call<List<PlaceModel>>
        @GET("/getSearchHistoryForUser")
        fun getSearchHistoryForUser(@Query("userid") userid: Int):Call<List<PlaceModel>>
        @GET("/recommendedP")
        fun getRecommendedPlaces(@Query("user_id") user_id: Int,@Query("nationality") nationality: String):Call<List<PlaceModel>>
        @GET("/place")
        fun getPlace(@Query("id") id: Int):Call<PlaceModel>
        @GET("/search")
        fun search(@Query("query") query: String):Call<List<PlaceModel>>
        @POST("/interestsOfNewUser")
        fun newUserInterests(@Query("userid") userid: Int, @Body interests: List<String>): Call<String>
        @POST("/addActivity")
        fun addActivity(@Body activity: ActivityModel,@Query ("id") id: Int): Call<addActivityResponse>
        @GET("/getActivitesForOnePlace")
        fun getActivitesForOnePlace(@Query("placeid") placeid: Int):Call<List<ActivityModel>>
        @GET("/pendingActivities")
        fun getPendingActivities(): Call<List<ActivityModel>>
        @GET("/getActivities")
        fun getActivitiesOfEntrepreneur(@Query("enterprenuerid") enterprenuerid: Int): Call<List<ActivityModel>>
        @POST("/admin_AvtivityResponse")
        fun admin_AvtivityResponse(@Body admin_AvtivityResponseModel:admin_AvtivityResponseModel): Call<DeleteResponse>
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