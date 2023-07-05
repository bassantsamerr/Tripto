package com.example.tripto.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.adapter.MainAdapter
import com.example.tripto.model.MainModel
import com.example.tripto.utils.RetrievingData

class FirstFragment : Fragment() {
    val sharedPreference by lazy {
        requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
    }
    val userid by lazy {
        sharedPreference.getInt("ID", 0)
    }
    val nationality by lazy {
        sharedPreference.getString("COUNTRY", "")
    }

    private lateinit var collections: List<MainModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvMain)
        collections = listOf(
            MainModel("Recommended Places", RetrievingData.getRecommendedPlaces(userid,nationality!!)),
            MainModel("Top 10", RetrievingData.getTop10laces()),
            MainModel("Tour Packages", RetrievingData.getAllPlaces()),
            MainModel("All Places", RetrievingData.getAllPlaces())
        )
        recyclerView.adapter = MainAdapter(collections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}

