package com.dbernic.weatherlite.ui.screens.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dbernic.weatherlite.data.preferences.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel  @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    val isLocationSelected: MutableState<Boolean> = mutableStateOf(
        sharedPreferencesManager.isLocationSet()
    )
}