package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.example.SignUpResponseModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.R
import com.example.tripto.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(){

    private val service: ApiInterface = ApiInterface.create()
    var etUsername: EditText? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var conPassword: EditText? = null
    lateinit var currentUser: SignUpResponseModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val Nationalities = resources.getStringArray(R.array.nationalities)
        val imageView = findViewById<ImageView>(R.id.iv_spinner)
        val popupMenu = PopupMenu(this, imageView)
        Nationalities.forEach { nationality ->
            popupMenu.menu.add(nationality)
        }
        val nationality = findViewById<TextView>(R.id.tv_nationality)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val selectedNationality = menuItem.title
            nationality.text = selectedNationality // set the selected nationality on the TextView
            true
        }
        etUsername = findViewById(R.id.et_username)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        conPassword = findViewById(R.id.et_repeatedPassword)
        imageView.setOnClickListener { popupMenu.show() }
        val bt_createAccount_click = findViewById<TextView>(R.id.bt_createAccount)
        bt_createAccount_click.setOnClickListener {
            var user = UserModel(
                etEmail!!.text.toString(),
                20,
                nationality.text.toString(),
                nationality.text.toString(),
                etUsername!!.text.toString(),
                1,
                etPassword!!.text.toString()
            )
            val call: Call<SignUpResponseModel> = service.addUser(user)
            call.enqueue(object : Callback<SignUpResponseModel>{
                override fun onResponse(call: Call<SignUpResponseModel>, response: Response<SignUpResponseModel>) {
                    if (response.isSuccessful) {
                        val sharedPreference =getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
                        currentUser= response.body()!!
                        val editor = sharedPreference.edit()
                        editor.putString("PASSWORD",etPassword!!.text.toString())
                        editor.putString("EMAIL",currentUser.email)
                        editor.putInt("AGE", currentUser.age!!)
                        editor.putString("COUNTRY",currentUser.country)
                        editor.putString("USERNAME",currentUser.username)
                        editor.putInt("ROLEID",currentUser.roleId!!)
                        editor.putInt("ID",currentUser.id!!)
                        editor.putBoolean("ISACTIVE",currentUser.isActive!!)
                        editor.apply()
                        Toast.makeText(this@SignupActivity, "Register Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignupActivity, InterestsActivty::class.java)
                        startActivity(intent)
                    } else if (!response.isSuccessful) {
                        Toast.makeText(this@SignupActivity, "Already Have Account", Toast.LENGTH_SHORT).show()
                        Log.d("add search history api", response.toString())
                        Log.d("add search history api", response?.errorBody()?.string().toString())
                    }
                }
                override fun onFailure(call: Call<SignUpResponseModel>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, "Can not Register", Toast.LENGTH_SHORT).show()
                    Log.d("", t.message.toString())
                    Log.d("failure error", "Can not Register")
                }
            })
        }
    }



}