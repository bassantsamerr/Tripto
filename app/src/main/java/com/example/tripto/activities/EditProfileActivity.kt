package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tripto.R
import com.example.tripto.model.UserModel
import com.example.tripto.retrofit.ApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val service: ApiInterface = ApiInterface.create()

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
        var username=sharedPreference.getString("USERNAME","").toString()
        var nationality=sharedPreference.getString("COUNTRY","").toString()
        var email=sharedPreference.getString("EMAIL","").toString()
        val id=sharedPreference.getInt("ID",0)
        val age=sharedPreference.getInt("AGE",0)
        val roleid=sharedPreference.getInt("ROLEID",0)
        val password=sharedPreference.getString("PASSWORD","")
        tv_username.text=username
        tv_usernamevalue.text=username
        tv_nationality.text=nationality
        tv_email.text=email


        val bt_edit = findViewById<TextView>(R.id.edit_profile)


        bt_edit.setOnClickListener{
            val sharedPreference =getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            Log.d("new username",tv_usernamevalue.text.toString())
            editor.putString("USERNAME", tv_usernamevalue.text.toString())
            editor.putString("COUNTRY",tv_nationality.text.toString())
            editor.putString("EMAIL",tv_email.text.toString())
            editor.apply()
            username=sharedPreference.getString("USERNAME","").toString()
            nationality=sharedPreference.getString("COUNTRY","").toString()
            email=sharedPreference.getString("EMAIL","").toString()
            tv_username.text=username
            tv_usernamevalue.text=username
            tv_nationality.text=nationality
            tv_email.text=email
            val user = UserModel(
                email,
                age,
                nationality,
                username,
                roleid,
                password.toString()
            )
            Log.d("id edit user", id.toString())
            Log.d("user edit user", user.toString())
            val call: Call<ResponseBody> = service.editUser(id,user)
         call.enqueue(object : Callback<ResponseBody>{
             override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 if (response.isSuccessful) {
                     Toast.makeText(this@EditProfileActivity, "Account Updated Successfully", Toast.LENGTH_SHORT).show()
                     Log.d("edit api response", response.body().toString())
                 }
                  else if (!response.isSuccessful) {
                     Toast.makeText(this@EditProfileActivity, "Updating failed", Toast.LENGTH_SHORT).show()
                     Log.d("fail edit api", response.toString())
                     Log.d("fail edit api", response?.errorBody()?.string().toString())
                  }
                 }


             override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                 Log.d("on fail  edit ", t.toString())
             }

         })
        }
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