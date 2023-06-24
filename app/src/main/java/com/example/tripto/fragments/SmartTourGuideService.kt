package com.example.tripto.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tripto.ApiInterface
import com.example.tripto.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SmartTourGuideService.newInstance] factory method to
 * create an instance of this fragment.
 */
class SmartTourGuideService : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Float = 0.0f
    private var log: Float = 0.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_smarttourguide, container, false)

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("dddddebug", "dddddebug")
            // Request location updates
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    // Get the current location
                    val currentLocation = LatLng(it.latitude, it.longitude)
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(currentLocation)
                            .title("Current Location")
                            .icon(BitmapFromVector(requireContext(), R.drawable.marker_map_speaker))
                    )

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
                    Log.d("dddddebug", "dddddebug")
                    lat= it.latitude.toFloat()
                    log=it.longitude.toFloat()
                }

            }
        } else {

            // Request location permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
        googleMap.setOnMarkerClickListener(OnMarkerClickListener { marker -> // on marker click we are getting the title of our marker
            val mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
            }
            val baseUrl = "http://10.0.2.2:8000/nearestPlace"
            // Create a Uri.Builder instance
            val uriBuilder = Uri.parse(baseUrl).buildUpon()
            // Add query parameters
            uriBuilder.appendQueryParameter("latitude", lat.toString())
            uriBuilder.appendQueryParameter("longitude", log.toString())
            // Build the final URL
            val finalUrl = uriBuilder.build().toString()
            Log.d("ammar darb nar", "${lat} ${log}")
            Log.d("el url el lazez", finalUrl)
            print("final url is ${finalUrl}")
            mediaPlayer.apply {
                reset()
                setDataSource(finalUrl)
                prepareAsync()
                Log.d("da5al", "da5al")
                setOnPreparedListener { mp ->
                    mp.start()
                }
            }
            false
        })
    }
    private fun BitmapFromVector(context: Context, @DrawableRes vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }
}