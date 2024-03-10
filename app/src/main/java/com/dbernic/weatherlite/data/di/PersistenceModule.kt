package com.dbernic.weatherlite.data.di

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Secure
import com.dbernic.weatherlite.R
import com.dbernic.weatherlite.data.preferences.SharedPreferencesManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    @SuppressLint("HardwareIds")
    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        sharedPreferences: SharedPreferences,
    ): SharedPreferencesManager {
        return SharedPreferencesManager( sharedPreferences )
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().apply {
            setLenient()
            setPrettyPrinting()
        }.create()
    }
}