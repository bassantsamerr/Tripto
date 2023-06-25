package com.example.tripto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class EditProfileActivity : AppCompatActivity() {
    private val SELECT_IMAGE_REQUEST = 1
    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        profileImageView = findViewById(R.id.profileImageView)
        val selectImageButton: Button = findViewById(R.id.selectImageButton)

        // Set click listener for the select image button
        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, profileImageView.width, profileImageView.height, false)
                val drawable = BitmapDrawable(resources, scaledBitmap)
                profileImageView.setImageDrawable(drawable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
