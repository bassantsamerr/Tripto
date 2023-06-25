package com.example.tripto.model

import android.location.Location

data class NearbyPlaceModel(
    val placeName: String,
    val description: String,
    val image: String,
    val location: String,
    val latitude: Double,
    val address: String,
    val id: Int,
    val rating: Double,
    val longitude: Double
)

