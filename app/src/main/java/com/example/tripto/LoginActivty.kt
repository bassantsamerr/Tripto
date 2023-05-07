package com.example.tripto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Application started","Application started");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val bt_login_click = findViewById<Button>(R.id.bt_login)
        bt_login_click.setOnClickListener {

            Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val tv_signup_click = findViewById<TextView>(R.id.tv_signup)
        tv_signup_click.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}