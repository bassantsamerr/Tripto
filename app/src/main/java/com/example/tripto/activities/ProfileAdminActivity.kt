package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tripto.R
import com.example.tripto.model.DeleteResponse
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_admin)
        val service: ApiInterface = ApiInterface.create()
        val bt_editProfile_click =findViewById<Button>(R.id.edit_profile)
        val bt_show_activity_requests = findViewById<Button>(R.id.show_activity_requests)
        val bt_show_all_activities =findViewById<Button>(R.id.show_all_activities)


        bt_editProfile_click.setOnClickListener {
            val intent = Intent(this@ProfileAdminActivity, EditProfileActivity::class.java)
            startActivity(intent)
        }
        bt_show_activity_requests.setOnClickListener {
            val intent = Intent(this@ProfileAdminActivity, ShowAllActivityRequestsAdmin::class.java)
            startActivity(intent)
        }
        bt_show_all_activities.setOnClickListener(){
            val intent = Intent(this@ProfileAdminActivity, ShowAllActivitiesAdminActivity::class.java)
            startActivity(intent)
        }

        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val tv_username=findViewById<TextView>(R.id.textView2)
        val username=sharedPreference.getString("USERNAME","").toString()
        val password=sharedPreference.getString("PASSWORD","").toString()
        tv_username.text=username
        val bt_logout=findViewById<Button>(R.id.logout)
        bt_logout.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("TOKEN", "")
            Log.d("token logout", token.toString())
            val call: Call<DeleteResponse> = service.logout(token.toString())
            call.enqueue(object : Callback<DeleteResponse> {
                override fun onResponse(
                    call: Call<DeleteResponse>,
                    response: Response<DeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@ProfileAdminActivity, LoginActivty::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            this@ProfileAdminActivity,
                            "Username or password is incorrect, Please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (!response.isSuccessful) {
                        Log.d("fail logout api", response.toString())
                        Log.d("fail logout api", response.errorBody()?.string().toString())
                    }
                }

                override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }
    }
}