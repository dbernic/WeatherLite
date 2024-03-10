package com.dbernic.weatherlite.data.di

import com.dbernic.weatherlite.data.preferences.SharedPreferencesManager
import com.dbernic.weatherlite.data.repository.PreferencesRepository
import com.dbernic.weatherlite.data.repository.RestRepository
import com.dbernic.weatherlite.data.rest.RestApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRestRepository(restApi: RestApi) : RestRepository = RestRepository(restApi)

    @Singleton
    @Provides
    fun providesPreferencesRepository(
        preferencesManager: SharedPreferencesManager,
        gson: Gson
    ) : PreferencesRepository = PreferencesRepository(preferencesManager, gson)
}