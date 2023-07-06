import android.os.Parcel
import android.os.Parcelable

data class ActivityModel(
  val name: String,
  val description: String,
  val location: String,
  val image: String,
  val place_id: Int,
  val Phone: String,
  val price: Int,
  val Time: Int,
  val socialmedia: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readInt(),
    parcel.readInt(),
    parcel.readString() ?: ""
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(description)
    parcel.writeString(location)
    parcel.writeString(image)
    parcel.writeInt(place_id)
    parcel.writeString(Phone)
    parcel.writeInt(price)
    parcel.writeInt(Time)
    parcel.writeString(socialmedia)
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
