package com.example.hokkung.cardemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapCallback(val context: Context) : OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private var mMap: GoogleMap? = null
    private val tag by lazy { "GoogleMapProviders" }
    private var zoomDefault = 15f
    private var fusedLocationProvider: FusedLocationProviderClient? = null
    private var currentAddress: Address? = null

    init {
        fusedLocationProvider = FusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        try {
            val location = fusedLocationProvider?.lastLocation
            location?.addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val currentLocation: Location? = it.result
                    currentLocation?.let {
                        val latlng = LatLng(it.latitude, it.longitude)
                        moveCamera(latlng)
                        addMarker(latlng, address = getAddress(latlng))
                    }
                    currentLocation?.let { Log.v(tag, it.toString()) }
                } else {
                    Log.e(tag, "cannot get location")
                }
            }
        } catch (e: Exception) {}

    }

    private fun getGeoLocateByLatlng(latlng: LatLng) {
        moveCamera(latlng)
        addMarker(latlng, address = getAddress(latlng))
    }


    private fun getAddress(latlng: LatLng): Address?  {
        val geoCoder = Geocoder(context)
        try {
            val addresses = geoCoder.getFromLocation(latlng.latitude, latlng.longitude, 1)
            if (addresses.isNotEmpty()) {
                val address: Address = addresses[0]
                Log.v(tag, address?.toString())
                return address
            }
        } catch (e: Exception) {}
        return null
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let { it ->
            mMap = it
            mMap?.isMyLocationEnabled = true
            mMap?.setOnMyLocationButtonClickListener(this)
            mMap?.setOnMyLocationClickListener(this)

            mMap?.setOnMapClickListener { latlng ->
                getGeoLocateByLatlng(latlng)
            }
        }
    }

    private fun addMarker(latlng: LatLng, address: Address?) {
        mMap?.clear()
        mMap?.addMarker(MarkerOptions().position(latlng).title(address?.getAddressLine(0)))
        currentAddress = address
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(context, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(location: Location) {
        val latlng = LatLng(location.latitude, location.longitude)
        moveCamera(latlng)
        addMarker(latlng, address = getAddress(latlng))
    }

    fun moveCamera(latlng: LatLng, zoom: Float = zoomDefault) {
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom))
    }

    fun getLocationToSave() = currentAddress


}