package com.dbernic.weatherlite.ui.screens.list

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dbernic.weatherlite.R
import com.dbernic.weatherlite.utils.DateUtils
import com.google.gson.Gson
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateDetails: (String) -> Unit,
    navigateLocation: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val weatherList by viewModel.weatherList.collectAsState()

    val activity = (LocalContext.current as Activity)
    BackHandler { activity.finish() }

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
                    modifier = Modifier.padding(32.dp,0.dp),
                    text = stringResource(id = R.string.weather_title)
                )
            },
            actions = {
                IconButton(
                    onClick = { navigateLocation() },
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                    )

                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            items(weatherList) { weather ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable {
                            navigateDetails(Gson().toJson(weather))
                        },
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        modifier = Modifier.padding(end = 20.dp),
                        fontSize = 16.sp,
                        text = DateUtils.getDate(weather.dt)
                    )
                    AsyncImage(
                        modifier = Modifier.padding(end = 20.dp),
                        model = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png",
                        contentDescription = weather.weather[0].description
                    )

                    Text(
                        fontSize = 16.sp,
                        text = "${weather.temp.min} ... ${weather.temp.max}"
                    )

                }
            }
        }

    }
}