package com.dbernic.weatherlite.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun getDate(time:Long):String {
        val date = Date(time*1000L)
        val sdf = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())

        return sdf.format(date);
    }
}