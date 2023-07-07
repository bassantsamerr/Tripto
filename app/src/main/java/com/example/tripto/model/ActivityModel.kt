import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class ActivityModel(
  val id: Int,
  val name: String,
  val description: String,
  val location: String,
  val image: String,
  val enterpreneur_id: Int,
  val place_id: Int,
  val Phone: String,
  val price: Int,
  val Time: Int,
  val socialmedia: String,
  val is_active: Boolean
) : Parcelable {
  @RequiresApi(Build.VERSION_CODES.Q)
  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readString() ?: "",
    parcel.readInt(),
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readInt(),
    parcel.readInt(),
    parcel.readString() ?: "",
    parcel.readBoolean()
  )

  constructor(
    name: String,
    description: String,
    location: String,
    image: String,
    enterpreneur_id: Int,
    place_id: Int,
    Phone: String,
    price: Int,
    Time: Int,
    socialmedia: String
  ) : this(
    0,
    name,
    description,
    location,
    image,
    enterpreneur_id,
    place_id,
    Phone,
    price,
    Time,
    socialmedia,
    false
  )

  @RequiresApi(Build.VERSION_CODES.Q)
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(id)
    parcel.writeString(name)
    parcel.writeString(description)
    parcel.writeString(location)
    parcel.writeString(image)
    parcel.writeInt(enterpreneur_id)
    parcel.writeInt(place_id)
    parcel.writeString(Phone)
    parcel.writeInt(price)
    parcel.writeInt(Time)
    parcel.writeString(socialmedia)
    parcel.writeBoolean(is_active)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<ActivityModel> {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun createFromParcel(parcel: Parcel): ActivityModel {
      return ActivityModel(parcel)
    }

    override fun newArray(size: Int): Array<ActivityModel?> {
      return arrayOfNulls(size)
    }
  }
}
