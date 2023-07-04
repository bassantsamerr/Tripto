package com.example.tripto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.example.SignUpResponseModel
import com.example.tripto.R
import com.example.tripto.model.UserModel
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

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
            // Perform authentication checks
            if (etEmail!!.text.toString().isEmpty() || etUsername!!.text.toString().isEmpty() || etPassword!!.text.toString().isEmpty() || conPassword!!.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
            } else if (!etPassword!!.text.toString().equals(conPassword!!.text.toString())) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            }
            else if (!isEmailValid(etEmail!!.text.toString())) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            }
            else if (!isPasswordValid(etPassword!!.text.toString())) {
                Toast.makeText(this, "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.", Toast.LENGTH_SHORT).show();
            }
            else{
            var user = UserModel(
                etUsername!!.text.toString(),
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
                        editor.putString("NATIONALITY",currentUser.nationality)
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
    }}}

private fun isPasswordValid(password: String): Boolean {
    // Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    val pattern: Pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}
private fun isEmailValid(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val pattern = Pattern.compile(emailPattern)
    return pattern.matcher(email).matches()
}



