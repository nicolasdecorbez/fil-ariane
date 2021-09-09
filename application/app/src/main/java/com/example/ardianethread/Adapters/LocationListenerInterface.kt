package com.example.ardianethread.Adapters

import android.location.Location
import android.os.Bundle

interface LocationListenerInterface {
  fun onLocationChanged(localisation: Location)
  fun onProviderEnabled(fournisseur: String)
  fun onProviderDisabled(fournisseur: String)
  fun onStatusChanged(fournisseur: String, status : Int, extras: Bundle)

}