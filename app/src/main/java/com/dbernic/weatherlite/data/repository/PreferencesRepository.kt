package com.dbernic.weatherlite.data.repository

import com.dbernic.weatherlite.data.model.LatLng
import com.dbernic.weatherlite.data.preferences.SharedPreferencesManager
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val prefManager: SharedPreferencesManager,
    private val gson: Gson
) {
    private val IS_GPS = "is_gps_use"
    private val LAT_LON = "lat_lon_json"

    fun isLocationSet() : Boolean {
        return isGPS() || !prefManager.getString(LAT_LON, "").isNullOrEmpty()
    }

    fun isGPS(): Boolean = prefManager.getBoolean(IS_GPS)

    fun setLatLng(latLng: LatLng) {
        prefManager.putString(LAT_LON, gson.toJson(latLng))
    }

    fun getLatLng(): LatLng? {
        try {
            return gson.fromJson(prefManager.getString(LAT_LON, ""), LatLng::class.java)
        } catch (e: Exception) { return null }
    }

}