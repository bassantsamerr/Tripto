package com.example.tripto.activities

import ActivityModel
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.tripto.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.example.addActivityResponse
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import java.io.File

private lateinit var activityNameEditText: EditText
private lateinit var activityDescriptionEditText: EditText
private lateinit var priceEditText: EditText
private lateinit var timeEditText: EditText
private lateinit var locationEditText: EditText
private lateinit var longitudeEditText: EditText
private lateinit var latitudeEditText: EditText
private lateinit var phoneNumberEditText: EditText
private lateinit var socialMediaEditText: EditText
lateinit var Image:ImageView
private var placeId: Int = 0
private lateinit var sharedPreference: SharedPreferences
val service: ApiInterface = ApiInterface.create()
private var userid:Int=0
class FullscreenDialog : DialogFragment(), View.OnClickListener {
    private var callback: Callback? = null

    companion object {
        fun newInstance(): FullscreenDialog {
            return FullscreenDialog()
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreference = requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        placeId = sharedPreference.getInt("PLACEID", 0)
        userid=sharedPreference.getInt("ID",0)
        val view = inflater.inflate(R.layout.activity_fullscreen, container, false)
        val close = view.findViewById<ImageButton>(R.id.fullscreen_dialog_close)
        val action = view.findViewById<TextView>(R.id.fullscreen_dialog_action)

        activityNameEditText = view.findViewById(R.id.activityName)
        activityDescriptionEditText = view.findViewById(R.id.ActivtiyDescription)
        priceEditText = view.findViewById(R.id.price)
        timeEditText = view.findViewById(R.id.time)
        locationEditText = view.findViewById(R.id.location)
        longitudeEditText = view.findViewById(R.id.longitude)
        latitudeEditText = view.findViewById(R.id.latt)
        phoneNumberEditText = view.findViewById(R.id.phonenumber)
        socialMediaEditText = view.findViewById(R.id.socialmedia)

        close.setOnClickListener(this)
        action.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        val id = v.id

        when (id) {
            R.id.fullscreen_dialog_close -> {
                dismiss()
            }

            R.id.fullscreen_dialog_action -> {
                val name = activityNameEditText.text.toString()
                val description = activityDescriptionEditText.text.toString()
                val location = locationEditText.text.toString()
                val image = ""
                val place_id = placeId
                val Phone = phoneNumberEditText.text.toString()
                val price = priceEditText.text.toString()
                val Time = timeEditText.text.toString()
                val socialmedia = socialMediaEditText.text.toString()
                val activity = ActivityModel(
                    name = activityNameEditText.text.toString(),
                    description = activityDescriptionEditText.text.toString(),
                    location = locationEditText.text.toString(),
                    image = "",
                    place_id = placeId,
                    Phone = phoneNumberEditText.text.toString(),
                    price = 0,
                    Time = 0,
                    socialmedia = socialMediaEditText.text.toString()
                )
                Log.d("activity",activity.toString())

                callback?.onActionClick(activity, userid)
                dismiss()
            }
        }
    }

    interface Callback {
        fun onActionClick(activity: ActivityModel,userid:Int)
    }
    fun getImageUrlFromFile(context: Context, imageFile: File): String {
        val contentUri = Uri.fromFile(imageFile)
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(contentUri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val imagePath = cursor?.getString(columnIndex ?: 0)
        cursor?.close()

        return imagePath ?: ""
    }
}

