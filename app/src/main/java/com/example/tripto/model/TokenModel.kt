package com.example.tripto.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("access_token" ) var accessToken : String? = null,
    @SerializedName("token_type"   ) var tokenType   : String? = null
)
