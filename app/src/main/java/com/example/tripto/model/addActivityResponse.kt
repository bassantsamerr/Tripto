package com.example.example

import com.google.gson.annotations.SerializedName


data class addActivityResponse(

  @SerializedName("enterprenuer_id" ) var enterprenuerId : Int? = null,
  @SerializedName("activity_id"     ) var activityId     : Int? = null,
  @SerializedName("id"              ) var id             : Int? = null

)