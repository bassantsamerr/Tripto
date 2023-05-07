package com.example.tripto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val Nationalities = resources.getStringArray(R.array.nationalities)
        val imageView = findViewById<ImageView>(R.id.iv_spinner)
        val popupMenu = PopupMenu(this, imageView)
        Nationalities.forEach { nationality ->
            popupMenu.menu.add(nationality)
        }
        val textView = findViewById<TextView>(R.id.tv_nationality)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val selectedNationality = menuItem.title
            textView.text = selectedNationality // set the selected nationality on the TextView
            true
        }
        imageView.setOnClickListener { popupMenu.show() }
        val bt_createAccount_click = findViewById<TextView>(R.id.bt_createAccount)
        bt_createAccount_click.setOnClickListener {
            val intent = Intent(this, InterestsActivty::class.java)
            startActivity(intent)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiInterface = retrofit.create(ApiInterface::class.java)


    }
}