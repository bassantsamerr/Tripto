package com.example.tripto.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.tripto.EditProfileActivity
import com.example.tripto.HomeActivity
import com.example.tripto.R
class ProfileActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)
           val bt_editProfile_click = view.findViewById<Button>(R.id.edit_profile)
        bt_editProfile_click.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }


        return view
    }



}