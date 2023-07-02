package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tripto.R
import com.example.tripto.model.CurrentUserModel
import com.example.tripto.model.TokenModel
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivty : AppCompatActivity() {
    private val service: ApiInterface = ApiInterface.create()
    var etUsername: EditText? = null
    var etPassword: EditText? = null
    var token:TokenModel= TokenModel("","")
    lateinit var currentUser:CurrentUserModel
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("Application started", "Application started");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val bt_login_click = findViewById<Button>(R.id.bt_login)
        etUsername = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        val sharedPreference =getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)
        val getUsername=sharedPreference.getString("USERNAME","")
        val getPassword=sharedPreference.getString("PASSWORD","")
//        if(getUsername!=""&&getPassword!=""){
//            val intent = Intent(this@LoginActivty, HomeActivity::class.java)
//            startActivity(intent)
//        }
        bt_login_click.setOnClickListener {
            val call: Call<TokenModel> = service.login_for_access_token(username = etUsername!!.text.toString(),
                etPassword!!.text.toString())
            call.enqueue(object : Callback<TokenModel>{
                override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@LoginActivty, "Login Successfully", Toast.LENGTH_SHORT).show()
                        Log.d("success login token", response.body().toString())
                        token= response.body()!!
                        Log.d("tokentokentokentoken",token.accessToken.toString())
                        val call: Call<CurrentUserModel> = service.get_current_user("Bearer "+token?.accessToken.toString())
                        call.enqueue(object : Callback<CurrentUserModel>{
                            override fun onResponse(call: Call<CurrentUserModel>, response: Response<CurrentUserModel>
                            ) {
                                if(response.isSuccessful){
                                    currentUser= response.body()!!
                                    val editor = sharedPreference.edit()
                                    editor.putString("TOKEN","Bearer "+token?.accessToken.toString())
                                    editor.putString("PASSWORD",etPassword!!.text.toString())
                                    editor.putString("EMAIL",currentUser.email)
                                    editor.putInt("AGE",currentUser.age)
                                    editor.putString("COUNTRY",currentUser.country)
                                    editor.putString("USERNAME",currentUser.username)
                                    editor.putInt("ROLEID",currentUser.roleId)
                                    editor.putInt("ID",currentUser.id)
                                    editor.putBoolean("ISACTIVE",currentUser.isActive)
                                    editor.apply()
                                    Log.d("success current user api", response.body().toString())
                                    Log.d("current user",currentUser.toString())
                                }
                                if(!response.isSuccessful){
                                    Toast.makeText(this@LoginActivty, "Username or password is incorrect, Please try again", Toast.LENGTH_SHORT).show()
                                    Log.d("fail current user api", response.toString())
                                    Log.d("fail current user api", response?.errorBody()?.string().toString())
                                }
                            }

                            override fun onFailure(call: Call<CurrentUserModel>, t: Throwable) {
                                Log.d("fail awe login token", t.toString())
                            }
                        })
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
        Log.d("tokentoken",token.accessToken.toString())

    }

}