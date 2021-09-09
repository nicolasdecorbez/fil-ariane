package com.example.ardianethread.Adapters

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class LocationListener : LocationListenerInterface {
    override fun onLocationChanged(localisation: Location) {
        Log.d("GPS", "localisation : $localisation")
        val coordonnees = java.lang.String.format("Latitude : %f - Longitude : %f\n",
            localisation.latitude,
            localisation.longitude)
        Log.d("Location Changed", "coordonnees : $coordonnees")
    }

    override fun onProviderEnabled(fournisseur: String) {
        Log.d("Fournisseur Enabled",fournisseur)
    }

    override fun onProviderDisabled(fournisseur: String) {
        Log.d("Fournisseur Disabled",fournisseur)
    }

    override fun onStatusChanged(fournisseur: String, status: Int, extras: Bundle) {
        TODO("Not yet implemented")
    }
}