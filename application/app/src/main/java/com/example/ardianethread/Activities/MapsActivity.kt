package com.example.ardianethread.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ardianethread.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var latitude : Double = 0.0
    var longitude : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        GetLastKnownLocation()

        // Add a marker in Sydney and move the camera
        val myPosition = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(myPosition).title("My Position"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition))
    }

    fun GetLastKnownLocation(){
        var manager : LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var criterias = Criteria()

        criterias.accuracy = Criteria.ACCURACY_FINE;

        // l'altitude
        criterias.isAltitudeRequired = true;

        // la direction
        criterias.isBearingRequired = true;

        // la vitesse
        criterias.isSpeedRequired = true;

        // la consommation d'énergie demandée
        criterias.isCostAllowed = true;
        criterias.powerRequirement = Criteria.POWER_HIGH;

       var fournisseur = manager.getBestProvider(criterias, true);
        Log.d("GPS", "fournisseur : " + fournisseur);

        if (fournisseur != null) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            var location = manager.getLastKnownLocation(fournisseur)
            var listener = LocationListener {
                location ->
            }
            location?.let { listener.onLocationChanged(it) }
            manager.requestLocationUpdates(fournisseur, 15000, 10F,listener )
            Log.e("Permission :",
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .toString())
            Log.e("Granted",PackageManager.PERMISSION_GRANTED.toString())
             if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
                 if(location != null) {
                     val coordonnees = String.format("Latitude : %f - Longitude : %f\n",
                         location.latitude,
                         location.longitude)
                     Log.d("GPS", "coordonnees : $coordonnees")
                     val autres = String.format("Vitesse : %f - Altitude : %f - Cap : %f\n",
                         location.speed,
                         location.altitude,
                         location.bearing)
                     Log.d("GPS", autres)
                     //String timestamp = String.format("Timestamp : %d\n", localisation.getTime());
                     //Log.d("GPS", "timestamp : " + timestamp);
                     val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                     val date = Date(location.getTime())
                     Log.d("GPS", sdf.format(date))
                     longitude = location.longitude
                     latitude = location.latitude
                 }
            } else {
                var perm: Array<kotlin.String> = arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                ActivityCompat.requestPermissions(this,perm,100,)
            }
        }
    }
}