package com.dbernic.weatherlite.data.model

import com.google.gson.annotations.SerializedName

data class GeocodingResp(
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("country") val country: String,
)
