package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable

data class PlaceModel(
    val id: Int,
    val placeName: String,
    val description: String,
    val address: String,
    val image: String,
    val rating: Double,
    val location: String,
    val longitude: Double,
    val latitude: Double,
    val activities: Array<Activity>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.createTypedArray(Activity.CREATOR)!!
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(placeName)
        parcel.writeString(description)
        parcel.writeString(address)
        parcel.writeString(image)
        parcel.writeDouble(rating)
        parcel.writeString(location)
        parcel.writeDouble(longitude)
        parcel.writeDouble(latitude)
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
