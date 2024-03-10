package com.dbernic.weatherlite.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dbernic.weatherlite.ui.Destinations.DETAILS
import com.dbernic.weatherlite.ui.Destinations.LIST
import com.dbernic.weatherlite.ui.Destinations.LOCATION
import com.dbernic.weatherlite.ui.Destinations.SPLASH
import com.dbernic.weatherlite.ui.screens.details.DetailsScreen
import com.dbernic.weatherlite.ui.screens.list.ListScreen
import com.dbernic.weatherlite.ui.screens.location.LocationScreen
import com.dbernic.weatherlite.ui.screens.splash.SplashScreen

object Destinations {
    const val SPLASH = "splash"
    const val LOCATION = "location"
    const val LIST = "list"
    const val DETAILS = "details/{json}"
}
@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SPLASH) {
        composable(SPLASH) {
            SplashScreen(
                navigateLocation = { navController.navigate(LOCATION) },
                navigateList = { navController.navigate(LIST) },
            )
        }
        composable(LOCATION) {
            LocationScreen ( navigateList = { navController.navigate(LIST) } )
        }
        composable(LIST) {
            ListScreen (
                navigateDetails = { json -> navController.navigate("details/$json") },
                navigateLocation = { navController.navigate(LOCATION) }
            )
        }
        composable(DETAILS){
            val json  = it.arguments?.getString("json")
            DetailsScreen(json = json!!)
        }
    }
}