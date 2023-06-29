package com.example.tripto.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.R
import com.example.tripto.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), Callback<ResponseBody> {

    private val service: ApiInterface = ApiInterface.create()
    var etUsername: EditText? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var conPassword: EditText? = null
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
            val user = UserModel(
                etEmail!!.text.toString(),
                20,
                nationality.text.toString(),
                etUsername!!.text.toString(),
                1,
                etPassword!!.text.toString()
            )
            service.addUser(user)?.enqueue(this)
        }
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, InterestsActivty::class.java)
            startActivity(intent)
            response.body()?.string()?.let { Log.d("response body", it) }
        } else if (!response.isSuccessful) {
            Toast.makeText(this, "Already Have Account", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, InterestsActivty::class.java)
            startActivity(intent)
            response.body()?.string()?.let { Log.d("response body", it) }
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        Toast.makeText(this, "Can not Register", Toast.LENGTH_SHORT).show()
        Log.d("", t.message.toString())
        Log.d("failure error", "Can not Register")
    }

}