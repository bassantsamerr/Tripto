package com.example.tripto.activities

import com.example.tripto.model.Activity
import android.os.Bundle
import com.example.tripto.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment

private lateinit var activityNameEditText: EditText
private lateinit var activityDescriptionEditText: EditText
private lateinit var priceEditText: EditText
private lateinit var timeEditText: EditText
private lateinit var locationEditText: EditText
private lateinit var longitudeEditText: EditText
private lateinit var latitudeEditText: EditText
private lateinit var phoneNumberEditText: EditText
private lateinit var socialMediaEditText: EditText

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
                val price = priceEditText.text.toString()
                val time = timeEditText.text.toString()
                val location = locationEditText.text.toString()
                val longitude = longitudeEditText.text.toString()
                val latitude = latitudeEditText.text.toString()
                val socialMedia = socialMediaEditText.text.toString()

                val activity = Activity(
                    name = activityNameEditText.text.toString(),
                    description = activityDescriptionEditText.text.toString(),
                    placeId = 0, // Set the appropriate value for placeId
                    location = locationEditText.text.toString(),
                    image = "", // Set the appropriate value for image
                    phone = phoneNumberEditText.text.toString(), // Set the appropriate value for phone
                    price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0,
                    time = timeEditText.text.toString().toIntOrNull() ?: 0,
                    socialMedia = socialMediaEditText.text.toString()
                )
                println(activity)

                callback?.onActionClick(activity)
                dismiss()
            }
        }
    }

    interface Callback {
        fun onActionClick(activity: Activity)
    }
}
