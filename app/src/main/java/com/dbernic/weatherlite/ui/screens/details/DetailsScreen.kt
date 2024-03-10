package com.dbernic.weatherlite.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dbernic.weatherlite.R
import com.dbernic.weatherlite.data.model.Daily
import com.dbernic.weatherlite.data.model.Temperature
import com.dbernic.weatherlite.utils.DateUtils
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(json: String) {
    val weather = Gson().fromJson(json, Daily::class.java)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier.padding(32.dp, 0.dp),
                    text = "Weather for ${DateUtils.getDate(weather.dt)}"
                )
            }
        )

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        ) {

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    modifier = Modifier.padding(end = 20.dp),
                    model = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png",
                    contentDescription = weather.weather[0].description
                )
                Text(text = weather.summary)
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Temperature", fontSize = 20.sp
            )

            Text(text = "Min - ${weather.temp.min}")
            Text(text = "Max - ${weather.temp.max}")

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Humidity: ${weather.humidity}%", fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Pressure: ${weather.pressure}", fontSize = 20.sp
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "UVI: ${weather.uvi}", fontSize = 20.sp
            )

        }
    }
}