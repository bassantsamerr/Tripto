package com.example.tripto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("default/CreateUser_addUser_post")
    fun addUser(@Body post: User): Call<User>
}