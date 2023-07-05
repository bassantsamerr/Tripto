package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.example.SignUpResponseModel
import com.example.tripto.R
import com.example.tripto.retrofit.ApiInterface
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InterestsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interests)
        val buttonClick = findViewById<ImageButton>(R.id.Next)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        var selectedChips: List<String>? = null
        val service: ApiInterface = ApiInterface.create()
        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val userid = sharedPreference.getInt("ID", 0)
        chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            selectedChips = group.checkedChipIds.mapNotNull { id ->
                findViewById<Chip>(id)?.text.toString()
            }

        }

        buttonClick.setOnClickListener {
                val call: Call<String> = service.newUserInterests(userid, selectedChips!!)
                call.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.isSuccessful){

                            Toast.makeText(this@InterestsActivity, "interests successed", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@InterestsActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                        if(!response.isSuccessful){

                            Log.d("fail add interests api", response.toString())
                            Log.d("fail add interests  api", response?.errorBody()?.string().toString())
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("failure error", t.message.toString())
                    }
                })

                // Access the selectedChips list here as needed
                for (chipText in selectedChips!!) {
                    Log.d("Selected Interest", chipText)
                }

        }
    }
}
