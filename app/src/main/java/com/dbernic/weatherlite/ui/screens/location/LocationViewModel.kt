package com.dbernic.weatherlite.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbernic.weatherlite.data.model.GeocodingResp
import com.dbernic.weatherlite.data.model.LatLng
import com.dbernic.weatherlite.data.repository.PreferencesRepository
import com.dbernic.weatherlite.data.repository.RestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val restRepository: RestRepository,
    private val prefRepository: PreferencesRepository,
): ViewModel() {

    private val _isGPS = MutableStateFlow(false)
    val isGPS = _isGPS.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _locationsList = MutableStateFlow<List<GeocodingResp>>(listOf())
    val locationsList = _locationsList

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _latLng = MutableStateFlow<LatLng?>(null)
    val latLng = _latLng.asStateFlow()

    private val _isSaved = MutableStateFlow(false)
    val isSaved = _isSaved.asStateFlow()

    fun switchGPS(value: Boolean) {
        if (value) {
            _latLng.value = null
            _isSearching.value = false
            _searchText.value = ""

        }

        _isGPS.value = value
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        if (text.length < 2) {
            _locationsList.value = listOf()
            return
        }

        viewModelScope.launch {
            try {
                _locationsList.value = restRepository
                    .getCoordinates(text)
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMsg.value = "Network error"
            }
        }
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun setLatLng(location: GeocodingResp) {
        _searchText.value = "${location.name}, ${location.country}"
        _isSearching.value = false
        _latLng.value = LatLng(location.lat, location.lon)
    }

    fun resetError() {
        _errorMsg.value = ""
    }

    fun isLatLng (): Boolean = latLng.value != null

    fun saveLocation() {
        latLng.value?.let {
            prefRepository.setLatLng(it)
            _isSaved.value= true
        }
    }

}