package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.R.attr.height
import android.R.attr.width
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentAnimalBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import java.io.IOException


class AnimalFragment : BaseFragmentV2<FragmentAnimalBinding>(R.layout.fragment_animal),
    OnMapReadyCallback {
    private lateinit var address: String
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private val geocoder by lazy { Geocoder(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        val args: AnimalFragmentArgs by navArgs()
        val animalName = args.animalName
        binding.animalTextName.text = animalName.toString()
        val animalId = args.animalId
        binding.animalTextId.text = animalId
        val animalType = args.animalType
        binding.animalTextType.text = animalType
        val animalGender = args.animalGender
        binding.animalTextGender.text = animalGender
        val animalDesc = args.animalDesc
        binding.animalTextDescription.text = animalDesc
        val animalAge = args.animalAge
        binding.animalTextAge.text = animalAge
        address = args.animalAddress


        mapView = binding.animalMapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val address = getAddressFromJson() // Get the address from your JSON

        val location = getLocationFromAddress(address)
        if (location != null) {

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.paw)
            val smallMarker = Bitmap.createScaledBitmap(bitmap, 50, 50, false)

            val markerOptions = MarkerOptions()
                .position(location)
                .title("Location Title")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
            googleMap.addMarker(markerOptions)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

        }
    }

    private fun getLocationFromAddress(address: String): LatLng? {
        try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses!!.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                return LatLng(latitude, longitude)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getAddressFromJson(): String {
        // Parse your JSON and extract the address
        // For this example, I'm just returning a sample address
        return address
    }
}
