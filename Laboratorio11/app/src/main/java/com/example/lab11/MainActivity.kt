package com.example.lab11

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lab11.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    val PERMISSION_ID = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (allPermissionsGrantedGPS()){
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID)
        }

        binding.btndetectar.setOnClickListener{
            leerUbicacionActual()
        }
    }




    private fun allPermissionsGrantedGPS() = REQUIRED_PERMISSIONS_GPS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }



    private fun leerUbicacionActual(){
      if (checkPermission()){
          var locationMngr: LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
          if (isLocationEnabled(locationMngr)){

              var permisosAceptados : Boolean = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

              if (permisosAceptados){
                  mFusedLocationProviderClient.lastLocation.addOnCompleteListener(this){task ->
                      var location: Location? = task.result
                      if (location == null){
                          requestNewLocationData()
                      }else{
                          binding.lblongitud.text = "LONGITUD = " + location.latitude.toString()
                          binding.lblatitud.text = "LATITUD = " + location.longitude.toString()
                      }
                  }
              }

          }else{
              Toast.makeText(this, "Activa el GPS", Toast.LENGTH_LONG).show()
              val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
              startActivity(intent)
              this.finish()

          }
    } else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID)

      }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData(){
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())


    }

    private val mLocationCallback = object: LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult){
            var mLastLocation: Location = locationResult.lastLocation
            binding.lblongitud.text = "LONGITUD = " + mLastLocation.longitude.toString()
            binding.lblatitud.text = "LATITUD = " + mLastLocation.latitude.toString()
        }
    }

    private fun checkPermission(): Boolean{
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    companion object{
        private val REQUIRED_PERMISSIONS_GPS = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    }
}