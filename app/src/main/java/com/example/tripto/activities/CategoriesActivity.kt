package com.example.tripto.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.tripto.R
import com.example.tripto.model.PlaceModel
import com.example.tripto.utils.RetrievingData

private lateinit var chip2: Button
private lateinit var chip3: Button
private lateinit var chip4: Button
private lateinit var chip5: Button
private lateinit var chip6: Button
private lateinit var chip7: Button
private lateinit var chip8: Button
private lateinit var chip9: Button
private lateinit var chip10: Button
private lateinit var chip11: Button
private lateinit var chip12: Button

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        chip2 = findViewById(R.id.chip2)
        chip3 = findViewById(R.id.chip3)
        chip4 = findViewById(R.id.chip4)
        chip5 = findViewById(R.id.chip5)
        chip6 = findViewById(R.id.chip6)
        chip7 = findViewById(R.id.chip7)
        chip8 = findViewById(R.id.chip8)
        chip9 = findViewById(R.id.chip9)
        chip10 = findViewById(R.id.chip10)
        chip11 = findViewById(R.id.chip11)
        chip12 = findViewById(R.id.chip12)

        chip2.setOnClickListener { handleButtonClick("Natural") }
        chip3.setOnClickListener { handleButtonClick("Fun") }
        chip4.setOnClickListener { handleButtonClick("Romanian") }
        chip5.setOnClickListener { handleButtonClick("Pharaonic") }
        chip6.setOnClickListener { handleButtonClick("Coptic") }
        chip7.setOnClickListener { handleButtonClick("Islamic") }
        chip8.setOnClickListener { handleButtonClick("Ancient") }
        chip9.setOnClickListener { handleButtonClick("Adventure") }
        chip10.setOnClickListener { handleButtonClick("Heritage") }
        chip11.setOnClickListener { handleButtonClick("Medical") }
        chip12.setOnClickListener { handleButtonClick("Religious") }
    }
    private fun handleButtonClick(type: String) {
        Log.d("Type",type)
        places = RetrievingData.getplacescategories(type)
        catName=type
        Log.d("categories",RetrievingData.getplacescategories(type).toString())
        val intent = Intent(this, CategoryPlacesActivity::class.java)
        startActivity(intent)
    }
    companion object {
        lateinit var places: List<PlaceModel>
        lateinit var catName: String
    }
}