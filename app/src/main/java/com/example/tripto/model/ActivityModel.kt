package com.example.example

import android.os.Parcel
import android.os.Parcelable
import com.example.tripto.model.Activity
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class ActivityModel(

  @SerializedName("name"        ) var name: String?           = null,
  @SerializedName("description" ) var description: String?           = null,
  @SerializedName("location"    ) var location: String?           = null,
  @SerializedName("image"       ) var image: String? = null,
  @SerializedName("place_id"    ) var placeId: Int?              = null,
  @SerializedName("Phone"       ) var Phone: String?           = null,
  @SerializedName("price"       ) var price: Int?              = null,
  @SerializedName("Time"        ) var Time: Int?              = null,
  @SerializedName("socialmedia" ) var socialmedia: String?           = null

): Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString(),
    parcel.readString(),
    parcel.readInt(),
    parcel.readString(),
    parcel.readInt(),
    parcel.readInt(),
    parcel.readString()!!
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(description)
    parcel.writeString(location)
    parcel.writeString(image)
    parcel.writeInt(placeId!!)
    parcel.writeString(Phone)
    parcel.writeInt(price!!)
    parcel.writeInt(Time!!)
    parcel.writeString(socialmedia)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Activity> {
    override fun createFromParcel(parcel: Parcel): Activity {
      return Activity(parcel)
    }

    override fun newArray(size: Int): Array<Activity?> {
      return arrayOfNulls(size)
    }
  }
  override fun toString(): String {
    return "ActivityModel(name=$name, description=$description, placeId=$placeId, " +
            "location=$location, image=$image, phone=$Phone, price=$price, time=$Time, " +
            "socialMedia=$socialmedia)"
  }
}
