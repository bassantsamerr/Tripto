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
import com.example.tripto.R
import androidx.recyclerview.widget.RecyclerView
import com.example.tripto.activities.DetailedActivity
import com.example.tripto.activities.ShowFavoritesActivity
import com.example.tripto.model.*
import com.example.tripto.retrofit.ApiInterface
import com.example.tripto.utils.RetrievingData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    val service: ApiInterface = ApiInterface.create()
    var searchResults: ArrayList<PlaceModel>
    init {
        searchResults = ArrayList()
    }
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
        // Set a touch listener for the drawable in the search EditText
        searchEditText.setOnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP && event.rawX >= searchEditText.right - searchEditText.compoundDrawables[drawableRight].bounds.width()) {
                performSearch(searchEditText.text.toString())
                return@setOnTouchListener true
            }
            false
        }

        searchAdapter.onItemClick = { nearbyPlaceModel ->
            val intent = Intent(requireContext(), DetailedActivity::class.java)
            intent.putExtra("nearbyplacemodel", nearbyPlaceModel)
            Log.d("place id",nearbyPlaceModel.id.toString())
            val sharedPreference = requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val userid = sharedPreference.getInt("ID", 0)
            val placetouser = PlaceToUserSearchModel(nearbyPlaceModel.id, userid)
            Log.d("nearbyPlaceModel.id", nearbyPlaceModel.id.toString())
            Log.d("userid search", userid.toString())
            val callClick: Call<ResponseBody> = service.addSearchHistory(placetouser)
            callClick.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    searchAdapter = SearchAdapter(RetrievingData.getSearchHistoryPlaces(requireContext()))
                    recyclerView.adapter = searchAdapter
                    if (response.isSuccessful) {
                        Log.d("add search history response", response.body().toString())
                    } else if (!response.isSuccessful) {
                        Log.d("add search history api", response.toString())
                        Log.d("add search history api", response.errorBody()?.string().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("on fail add search history", t.toString())
                }
            })
            startActivity(intent)
        }


    }
    private fun performSearch(query:String) :List<PlaceModel>{
        val call: Call<List<PlaceModel>> = service.search(searchEditText.text.toString())
        call.enqueue(object : Callback<List<PlaceModel>> {
            override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
                if (response.isSuccessful) {
                    searchResults.clear()

                    for (myData in response.body()!!) {
                        searchResults.add(myData)
                    }
                    for (placeModel in searchResults) {
                        Log.d("searchresult", placeModel.toString())
                    }
                    val updatedList = ArrayList(searchResults)
                    searchAdapter.updateData(updatedList)
                    recyclerView.adapter = searchAdapter
                } else {
                    Log.d("fail searchresult", response.toString())
                    Log.d("fail searchresult", response.errorBody()?.string().toString())
                }
            }


            override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                Log.d("fail searchresult", t.toString())
            }
        })
        return searchResults
    }

    companion object {
         var searchAdapter: SearchAdapter

        init {
            searchAdapter = SearchAdapter(ArrayList()) // Initialize with an empty ArrayList
        }
    }



}
