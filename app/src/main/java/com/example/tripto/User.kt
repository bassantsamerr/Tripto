package com.example.tripto
import com.google.gson.annotations.SerializedName

data class User(
  val email: String,
  val age: Int,
  val country: String,
  val username: String,
  val role_id: Int,
  val password: String
)



