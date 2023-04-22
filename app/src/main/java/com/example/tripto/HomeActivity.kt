package com.example.tripto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tripto.adapter.MainAdapter
import com.example.tripto.databinding.ActivityMainBinding
import com.example.tripto.utils.SampleData

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the RecyclerView adapter using the binding object
        binding.rvMain.adapter = MainAdapter(SampleData.collections)
    }
}
