package com.composemates.composecode.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CurrentLocation() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val coroutineScope = rememberCoroutineScope()

    @SuppressLint("MissingPermission")
    fun fetchLocation() {
        val fineLocationPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fineLocationPermissionGranted || coarseLocationPermissionGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { loc: Location? ->
                location = loc
            }
        } else {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    // Request location when the composable is first composed
    LaunchedEffect(locationPermissionState) {
        fetchLocation()
    }

    Column {
        location?.let {
            Text(text = "Latitude: ${it.latitude}, Longitude: ${it.longitude}")
        } ?:   Text(text = "Fetching location...")

        if (!locationPermissionState.allPermissionsGranted) {
            Text(text = "Location permissions are required to get the current location.")
        }
    }
}