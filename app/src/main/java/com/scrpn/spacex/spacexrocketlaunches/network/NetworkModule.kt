package com.scrpn.spacex.spacexrocketlaunches.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.scrpn.spacex.spacexrocketlaunches.BuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SPACEX_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideNytAPI(retrofit: Retrofit): SpaceXApi = retrofit.create(SpaceXApi::class.java)

}