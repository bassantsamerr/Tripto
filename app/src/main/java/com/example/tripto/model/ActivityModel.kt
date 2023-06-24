package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable

data class ActivityModel(
    val title: String,
    val imageUrl: String,
    val description:String,
    val ratings: Double,
    val location: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeDouble(ratings)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActivityModel> {
        override fun createFromParcel(parcel: Parcel): ActivityModel {
            return ActivityModel(parcel)
        }

        override fun newArray(size: Int): Array<ActivityModel?> {
            return arrayOfNulls(size)
        }
    }
}
