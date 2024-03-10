package com.dbernic.weatherlite.ui.screens.location

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dbernic.weatherlite.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    navigateList: () -> Unit,
    viewModel: LocationViewModel = hiltViewModel()
) {

    val checked by viewModel.isGPS.collectAsState()

    val error = viewModel.errorMsg.collectAsState()
    if (error.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, error.value, Toast.LENGTH_LONG).show()
        viewModel.resetError()
    }

    val isSaved = viewModel.isSaved.collectAsState()
    if (isSaved.value) navigateList()

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
                    text = stringResource(id = R.string.location_title)
                )
            },
            actions = {
                IconButton(
                    onClick = { viewModel.saveLocation() },
                    enabled = checked ||  viewModel.isLatLng()
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save",
                    )

                }
            }
        )
        if (false) { // TODO Get current geolocation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(id = R.string.use_gps_title),
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1.0F)
                )
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        viewModel.switchGPS(it)
                    }
                )
            }
        }

        if (!checked) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                val searchText by viewModel.searchText.collectAsState()
                val isSearching by viewModel.isSearching.collectAsState()
                val locationsList by viewModel.locationsList.collectAsState()
                var active by remember { mutableStateOf(false) }

                Text(
                    text = stringResource(id = R.string.select_location_title),
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                DockedSearchBar(
                    query = searchText,
                    onQueryChange = viewModel::onSearchTextChange,
                    onSearch = viewModel::onSearchTextChange,
                    active = isSearching,
                    onActiveChange = { viewModel.onToggleSearch() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_placeholder)
                        )
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        if (isSearching) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (searchText.isNotEmpty()) {
                                        viewModel.onSearchTextChange("")
                                    } else {
                                        viewModel.onToggleSearch()
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    },


                ) {
                    LazyColumn(
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {
                        items(locationsList) { location ->
                            Text(
                                text = "${location.name}, ${location.country}",
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 16.dp, top = 16.dp)
                                    .clickable {
                                        viewModel.setLatLng(location)
                                    },
                            )
                        }
                    }
                }

            }
        }

    }
}

@Preview
@Composable
fun LocationPreview() {
    LocationScreen({})
}