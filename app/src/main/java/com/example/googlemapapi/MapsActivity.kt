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
}