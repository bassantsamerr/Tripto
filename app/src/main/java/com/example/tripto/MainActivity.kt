package com.example.tripto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonClick = findViewById<Button>(R.id.bt_login)
        buttonClick.setOnClickListener {
            val intent = Intent(this, interests::class.java)
            startActivity(intent)
        }
    }
}