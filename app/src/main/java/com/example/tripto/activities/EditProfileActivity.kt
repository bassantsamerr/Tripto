package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tripto.R

class EditProfileActivity : AppCompatActivity() {
    private val SELECT_IMAGE_REQUEST = 1
    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        profileImageView = findViewById(R.id.profileImageView)
        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        val Nationalities = resources.getStringArray(R.array.nationalities)
        val imageView = findViewById<ImageView>(R.id.iv_spinner)
        val popupMenu = PopupMenu(this, imageView)
        Nationalities.forEach { nationality ->
            popupMenu.menu.add(nationality)
        }
        val nationalityv = findViewById<TextView>(R.id.nationality_value)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val selectedNationality = menuItem.title
            Log.d("Nationality","hi")
            nationalityv.text = selectedNationality // set the selected nationality on the TextView
            true
        }
        imageView.setOnClickListener { popupMenu.show() }
        // Set click listener for the select image button
        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }
        val sharedPreference =getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val tv_username=findViewById<TextView>(R.id.textView2)
        val tv_usernamevalue=findViewById<TextView>(R.id.username_value)
        val tv_nationality=findViewById<TextView>(R.id.nationality_value)
        val tv_email=findViewById<TextView>(R.id.email_value)
        val username=sharedPreference.getString("USERNAME","").toString()
        val nationality=sharedPreference.getString("COUNTRY","").toString()
        val email=sharedPreference.getString("EMAIL","").toString()
        tv_username.text=username
        tv_usernamevalue.text=username
        tv_nationality.text=nationality
        tv_email.text=email
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                val scaledBitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    profileImageView.width,
                    profileImageView.height,
                    false
                )
                val drawable = BitmapDrawable(resources, scaledBitmap)
                profileImageView.setImageDrawable(drawable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}