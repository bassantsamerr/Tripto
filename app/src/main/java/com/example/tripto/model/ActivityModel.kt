package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable

data class ActivityModel(
    val name: String,
    val description: String,
    val placeId: Int,
    val location: String,
    val image: String,
    val phone: String,
    val price: Double,
    val time: Int,
    val socialMedia: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(placeId)
        parcel.writeString(location)
        parcel.writeString(image)
        parcel.writeString(phone)
        parcel.writeDouble(price)
        parcel.writeInt(time)
        parcel.writeString(socialMedia)
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
    override fun toString(): String {
        return "ActivityModel(name=$name, description=$description, placeId=$placeId, " +
                "location=$location, image=$image, phone=$phone, price=$price, time=$time, " +
                "socialMedia=$socialMedia)"
    }
}
