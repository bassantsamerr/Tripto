package com.example.tripto.fragments
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tripto.activities.EditProfileActivity
import com.example.tripto.R
import com.example.tripto.activities.LoginActivty
import com.example.tripto.activities.ShowFavoritesActivity
import com.example.tripto.activities.ShowMyActivities
import com.example.tripto.model.DeleteResponse
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)
        val service: ApiInterface = ApiInterface.create()
        val bt_editProfile_click = view.findViewById<Button>(R.id.edit_profile)
        val bt_show_favorites = view.findViewById<Button>(R.id.showFav)
        val bt_show_myActivities = view.findViewById<Button>(R.id.myActivities)


        bt_editProfile_click.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
        bt_show_favorites.setOnClickListener {
            val intent = Intent(requireContext(), ShowFavoritesActivity::class.java)
            startActivity(intent)
        }
        bt_show_myActivities.setOnClickListener(){
            val intent = Intent(requireContext(), ShowMyActivities::class.java)
            startActivity(intent)
        }

        val sharedPreference = requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val tv_username=view.findViewById<TextView>(R.id.textView2)
        val username=sharedPreference.getString("USERNAME","").toString()
        val password=sharedPreference.getString("PASSWORD","").toString()
        tv_username.text=username
        val bt_logout=view.findViewById<Button>(R.id.logout)
        bt_logout.setOnClickListener {
            val sharedPreference = requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("TOKEN", "")
            Log.d("token logout",token.toString())
            val call: Call<DeleteResponse> = service.logout(token.toString())
            call.enqueue(object : Callback<DeleteResponse> {
                override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                    if(response.isSuccessful){
                        val intent = Intent(requireContext(), LoginActivty::class.java)
                        startActivity(intent)
                        Toast.makeText(requireContext(), "Username or password is incorrect, Please try again", Toast.LENGTH_SHORT).show()
                    }
                    if(!response.isSuccessful){
                        Log.d("fail logout api", response.toString())
                        Log.d("fail logout api", response?.errorBody()?.string().toString())
                    }
                }

                override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }
        return view
    }





}