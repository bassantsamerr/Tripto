package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable

data class TourPackageModel(
    val packageName: String,
    val places: ArrayList<PlaceModel>
) {

}
