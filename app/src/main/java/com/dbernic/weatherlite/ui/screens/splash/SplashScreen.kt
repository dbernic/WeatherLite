package com.dbernic.weatherlite.ui.screens.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dbernic.weatherlite.R

@Composable
fun SplashScreen(
    navigateLocation: ()-> Unit,
    navigateList: ()-> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val isLocationSet = viewModel.isLocationSelected
    if (isLocationSet.value) navigateList() else navigateLocation()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(240.dp)
                    .width(240.dp)

            )
        }

    }
}

@Preview
@Composable
fun SplashPreview(){
    SplashScreen({}, {})
}