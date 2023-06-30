package com.example.tripto.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tripto.R
import com.example.tripto.model.TokenModel
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivty : AppCompatActivity() {
    private val service: ApiInterface = ApiInterface.create()
    var etUsername: EditText? = null
    var etPassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("Application started", "Application started");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val bt_login_click = findViewById<Button>(R.id.bt_login)
        etUsername = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)


        bt_login_click.setOnClickListener {
            val call: Call<TokenModel> = service.login_for_access_token(username = etUsername!!.text.toString(),
                etPassword!!.text.toString())
            call.enqueue(object : Callback<TokenModel>{
                override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@LoginActivty, "Login Successfully", Toast.LENGTH_SHORT).show()
                        Log.d("success login token", response.body().toString())
                        val intent = Intent(this@LoginActivty, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    if(!response.isSuccessful){
                        Toast.makeText(this@LoginActivty, "Username or password is incorrect, Please try again", Toast.LENGTH_SHORT).show()
                        Log.d("fail login token", response.toString())
                        Log.d("fail login token", response?.errorBody()?.string().toString())
                    }
                }

                override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                    Log.d("fail awe login token", t.toString())
                }
            })


        }
        val tv_signup_click = findViewById<TextView>(R.id.tv_signup)
        tv_signup_click.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }
    }
}