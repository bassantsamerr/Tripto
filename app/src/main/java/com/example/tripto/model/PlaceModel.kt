package com.example.tripto.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PlaceModel(
    @SerializedName("placeName") var placeName: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("latitude") var latitude: Double,
    @SerializedName("address") var address: String? = null,
    @SerializedName("id") var id: Int,
    @SerializedName("rating") var rating: Double,
    @SerializedName("longitude") var longitude: Double,
    @SerializedName("ticketPrice") var ticketPrice: Double,
    @SerializedName("estimatedDuration") var estimatedDuration: Double

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(placeName)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(location)
        parcel.writeDouble(latitude)
        parcel.writeString(address)
        parcel.writeInt(id)
        parcel.writeDouble(rating)
        parcel.writeDouble(longitude)
        parcel.writeDouble(ticketPrice)
        parcel.writeDouble(estimatedDuration)
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

