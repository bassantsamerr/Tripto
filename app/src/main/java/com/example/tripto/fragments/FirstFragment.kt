package com.example.tripto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.R
import com.example.tripto.adapter.MainAdapter
import com.example.tripto.utils.SampleData

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvMain)
        // Set the RecyclerView adapter
        recyclerView.adapter = MainAdapter(SampleData.collections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}
