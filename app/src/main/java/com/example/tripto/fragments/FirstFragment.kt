package com.example.tripto.fragments

import TourpackageAdapter
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
import com.example.tripto.model.PlaceModel
import com.example.tripto.model.TourPackageModel
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
        val recyclerViewtp: RecyclerView = view.findViewById(R.id.tourpackageRview)
        collections = listOf(
            MainModel("Recommended Places", RetrievingData.getRecommendedPlaces(userid,nationality!!)),
            MainModel("Top 10", RetrievingData.getTop10places()),
            MainModel("All Places", RetrievingData.getAllPlaces())
        )
        val place1 = PlaceModel(
            placeName = "Eiffel Tower",
            description = "A wrought-iron lattice tower in Paris.",
            image = "https://www.cleopatraegypttours.com/wp-content/uploads/2018/08/Mosque-of-Amr-ibn-al-Aas-1200x1090.jpg",
            location = "Paris",
            latitude = 48.8584,
            address = "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France",
            id = 1,
            rating = 4.5,
            longitude = 2.2945,
            ticketPrice = 10.0,
            estimatedDuration = 2.5
        )

        val place2 = PlaceModel(
            placeName = "Statue of Liberty",
            description = "A colossal neoclassical sculpture on Liberty Island.",
            image ="https://www.cleopatraegypttours.com/wp-content/uploads/2018/08/Mosque-of-Amr-ibn-al-Aas-1200x1090.jpg",
            location = "New York",
            latitude = 40.6892,
            address = "Liberty Island, New York, NY 10004, United States",
            id = 2,
            rating = 4.8,
            longitude = -74.0445,
            ticketPrice = 15.0,
            estimatedDuration = 3.0
        )

        val place3 = PlaceModel(
            placeName = "Taj Mahal",
            description = "An ivory-white marble mausoleum in Agra.",
            image = "https://www.cleopatraegypttours.com/wp-content/uploads/2018/08/Mosque-of-Amr-ibn-al-Aas-1200x1090.jpg",
            location = "Agra",
            latitude = 27.1751,
            address = "Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001, India",
            id = 3,
            rating = 4.7,
            longitude = 78.0421,
            ticketPrice = 20.0,
            estimatedDuration = 4.5
        )

        val tourPackage = listOf( TourPackageModel(
            packageName = "TourPackage1",
            places = arrayListOf(place1, place2, place3)
        )
        )

        recyclerViewtp.adapter=TourpackageAdapter(tourPackage)
        recyclerViewtp.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = MainAdapter(collections)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
