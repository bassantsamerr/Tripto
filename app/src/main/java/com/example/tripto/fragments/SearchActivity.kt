package com.example.tripto.fragments

import SearchAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.tripto.R
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.DetailedActivity
import com.example.tripto.PlaceActivity
import com.example.tripto.utils.SampleData

class SearchActivity : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.searchRvMain)
        searchEditText = view.findViewById(R.id.searchEditText)

        // Set the RecyclerView adapter
        searchAdapter = SearchAdapter(SampleData.collections[3].placeModels)
        recyclerView.adapter = searchAdapter

        // Set a touch listener for the drawable in the search EditText
        searchEditText.setOnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP && event.rawX >= searchEditText.right - searchEditText.compoundDrawables[drawableRight].bounds.width()) {
                performSearch()
                return@setOnTouchListener true
            }
            false
        }
    }

    private fun performSearch() {
        val query = searchEditText.text.toString().trim()
        // Filter the data based on the search query
        val filteredData = SampleData.collections[3].placeModels.filter { placeModel ->
            placeModel.title.contains(query, ignoreCase = true) ||
                    placeModel.location.contains(query, ignoreCase = true)
        }

        // Update the adapter with the filtered data
        searchAdapter.updateData(filteredData)
    }

}
