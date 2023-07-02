package com.example.tripto.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.tripto.activities.EditProfileActivity
import com.example.tripto.R
import com.example.tripto.activities.ShowFavoritesActivity

class ProfileActivity : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)

        val bt_editProfile_click = view.findViewById<Button>(R.id.edit_profile)
        val bt_show_favorites = view.findViewById<Button>(R.id.showFav)
        bt_editProfile_click.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
        bt_show_favorites.setOnClickListener {
            val intent = Intent(requireContext(), ShowFavoritesActivity::class.java)
            startActivity(intent)
        }
        val sharedPreference = requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val tv_username=view.findViewById<TextView>(R.id.textView2)
        val username=sharedPreference.getString("USERNAME","").toString()
        val password=sharedPreference.getString("PASSWORD","").toString()
        tv_username.text=username

        return view
    }





}