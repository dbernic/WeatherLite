package com.dbernic.weatherlite.data.preferences

import android.content.SharedPreferences
import com.dbernic.weatherlite.data.model.LatLng
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
) {
    val IS_GPS = "is_gps_use"
    val LAT_LON = "lat_lon_json"

    fun isLocationSet() : Boolean {
        return getBoolean(IS_GPS) || !getString(LAT_LON, "").isNullOrEmpty()
    }

    fun isGPS(): Boolean = getBoolean(IS_GPS)

    fun getLatLon(): LatLng? {
        try {
            return gson.fromJson(getString(LAT_LON, ""), LatLng::class.java)
        } catch (e: Exception) { return null }
    }


    private fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    private fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    private fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    private fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

}