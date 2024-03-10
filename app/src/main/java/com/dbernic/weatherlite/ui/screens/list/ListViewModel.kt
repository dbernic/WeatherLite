package com.dbernic.weatherlite.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbernic.weatherlite.data.model.Daily
import com.dbernic.weatherlite.data.model.GeocodingResp
import com.dbernic.weatherlite.data.repository.PreferencesRepository
import com.dbernic.weatherlite.data.repository.RestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val restRepository: RestRepository,
    private val prefRepository: PreferencesRepository
) : ViewModel() {

    private val _weatherList = MutableStateFlow<ArrayList<Daily>>(arrayListOf())
    val weatherList = _weatherList

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    fun getWeather(){
        val latLng = prefRepository.getLatLng()!!
        viewModelScope.launch {
            try {
                _weatherList.value = restRepository
                    .getWeather(latLng.latitude.toFloat(), latLng.longitude.toFloat())!!.daily
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMsg.value = "Network error"
            }
        }
    }

    init {
        getWeather()
    }
}