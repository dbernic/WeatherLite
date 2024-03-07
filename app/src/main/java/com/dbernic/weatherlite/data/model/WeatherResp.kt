package com.dbernic.weatherlite.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResp (
    @SerializedName("lat") val lat: Float,
    @SerializedName("lon") val lon: Float,
    @SerializedName("daily") val daily: ArrayList<Daily>,
)

data class Daily (
    val sunrise: Long,
    val sunset: Long,
    val summary: String,
    val temp: Temperature,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Float,
    val weather: Weather,
    val uvi: Float,
)

data class Temperature (
    val day: Float,
    val night: Float,
    val min: Float,
    val max: Float,
)

data class Weather (
    val main: String,
    val description: String,
    val icon: String,
)
