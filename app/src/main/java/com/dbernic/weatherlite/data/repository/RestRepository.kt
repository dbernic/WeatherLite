package com.dbernic.weatherlite.data.repository

import com.dbernic.weatherlite.data.model.GeocodingResp
import com.dbernic.weatherlite.data.model.LatLng
import com.dbernic.weatherlite.data.model.Weather
import com.dbernic.weatherlite.data.model.WeatherResp
import com.dbernic.weatherlite.data.rest.RestApi

class RestRepository(
    private val api: RestApi
) {

    suspend fun getCoordinates(query: String): List<GeocodingResp> {
        return api.getGeocoding(query).body()?: listOf()
    }

    suspend fun getWeather(lat: Float, lon: Float): WeatherResp? {
        return api.getWeather(lat, lon).body()
    }
}