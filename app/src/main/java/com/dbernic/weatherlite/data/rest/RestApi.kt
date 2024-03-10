package com.dbernic.weatherlite.data.rest

import com.dbernic.weatherlite.BuildConfig
import com.dbernic.weatherlite.data.model.GeocodingResp
import com.dbernic.weatherlite.data.model.WeatherResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("geo/1.0/direct?limit=10&appid=${BuildConfig.API_KEY}")
    suspend fun getGeocoding(
        @Query("q") query: String
    ): Response<List<GeocodingResp>>

    @GET("data/3.0/onecall?exclude=current,minutely,hourly,alerts&appid=${BuildConfig.API_KEY}&units=metric")
    suspend fun getWeather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
    ): Response<WeatherResp>
}