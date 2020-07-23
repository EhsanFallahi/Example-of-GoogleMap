package com.example.googlemapapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val latitude=29.935775
        val longitude=52.891512
        //value of zoom level is between 1 - 20
        val zoomLevel=15f
        val perspoliceLatLng=LatLng(latitude,longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(perspoliceLatLng,zoomLevel))
        map.addMarker(MarkerOptions().position(perspoliceLatLng))

        setMapLongClick(map)
        setPoiClick(map)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infalter=menuInflater
        infalter.inflate(R.menu.map_options,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)=when(item.itemId){
        R.id.normal_map->{
            map.mapType=GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map->{
            map.mapType=GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map->{
            map.mapType=GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map->{
            map.mapType=GoogleMap.MAP_TYPE_TERRAIN
            true
        }else ->super.onOptionsItemSelected(item)
    }

    private fun setMapLongClick(map:GoogleMap)= map.setOnMapLongClickListener {
        val snippet= String.format(
            Locale.getDefault(),
            "Lat:%1$.5f,Long:%2$.5f",
            it.latitude,
            it.longitude
        )
            map.addMarker(MarkerOptions().position(it)
                .title("مکان شما")
                .snippet(snippet)
            )
        }

    private fun setPoiClick(map:GoogleMap) {
        GoogleMap.OnPoiClickListener {
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(it.latLng)
                    .title(it.name)
            )
            poiMarker.showInfoWindow()
        }
    }


}