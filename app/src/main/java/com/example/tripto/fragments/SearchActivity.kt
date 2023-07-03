package com.example.tripto.fragments

import SearchAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.tripto.R
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.activities.DetailedActivity
import com.example.tripto.model.DeleteResponse
import com.example.tripto.model.NearbyPlaceModel
import com.example.tripto.model.PlaceToUserModel
import com.example.tripto.model.PlaceToUserSearchModel
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.RetrievingData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    val service: ApiInterface = ApiInterface.create()
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
        searchAdapter = SearchAdapter(RetrievingData.getSearchHistoryPlaces(requireContext()))
        recyclerView.adapter = searchAdapter
        searchAdapter.onItemClick = { nearbyPlaceModel ->
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            intent.putExtra("nearbyplacemodel", nearbyPlaceModel)
            val sharedPreference =
                requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val userid = sharedPreference.getInt("ID", 0)
            val placetouser = PlaceToUserSearchModel(nearbyPlaceModel.id, userid)
            Log.d("nearbyPlaceModel.id", nearbyPlaceModel.id.toString())
            Log.d("userid search", userid.toString())
            val call: Call<ResponseBody> = service.addSearchHistory(placetouser)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    searchAdapter =
                        SearchAdapter(RetrievingData.getSearchHistoryPlaces(requireContext()))
                    recyclerView.adapter = searchAdapter
                    if (response.isSuccessful) {
                        Log.d("add search history response", response.body().toString())
                    } else if (!response.isSuccessful) {
                        Log.d("add search history api", response.toString())
                        Log.d("add search history api", response?.errorBody()?.string().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            startActivity(intent)
        }
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
        val filteredData = RetrievingData.collections[3].AllPlacesModel.filter { placeModel ->
            placeModel.placeName?.contains(query, ignoreCase = true) == true ||
                    placeModel.location?.contains(query, ignoreCase = true) == true
        }

        // Update the adapter with the filtered data
        searchAdapter.updateData(filteredData as ArrayList<NearbyPlaceModel>)
        searchAdapter.onItemClick = { nearbyPlaceModel ->
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            intent.putExtra("nearbyplacemodel", nearbyPlaceModel)
            startActivity(intent)
        }

    }

}
