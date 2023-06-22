package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable

data class PlaceModel(
    val title: String,
    val imageUrl: String,
    val description: String,
    val ratings: Double,
    val location: String,
    val activities: Array<ActivityModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.createTypedArray(ActivityModel.CREATOR)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeDouble(ratings)
        parcel.writeString(location)
        parcel.writeTypedArray(activities, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceModel> {
        override fun createFromParcel(parcel: Parcel): PlaceModel {
            return PlaceModel(parcel)
        }

        override fun newArray(size: Int): Array<PlaceModel?> {
            return arrayOfNulls(size)
        }
    }
}
