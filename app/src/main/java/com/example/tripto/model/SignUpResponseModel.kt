package com.example.example

import com.google.gson.annotations.SerializedName


data class SignUpResponseModel (

  @SerializedName("is_active"       ) var isActive       : Boolean? = null,
  @SerializedName("id"              ) var id             : Int?     = null,
  @SerializedName("email"           ) var email          : String?  = null,
  @SerializedName("country"         ) var country        : String?  = null,
  @SerializedName("role_id"         ) var roleId         : Int?     = null,
  @SerializedName("age"             ) var age            : Int?     = null,
  @SerializedName("hashed_password" ) var hashedPassword : String?  = null,
  @SerializedName("username"        ) var username       : String?  = null

)