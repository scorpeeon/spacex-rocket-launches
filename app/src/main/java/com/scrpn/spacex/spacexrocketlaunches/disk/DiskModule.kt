package com.scrpn.spacex.spacexrocketlaunches.disk

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DiskModule {
    companion object {
        private const val DB_NAME = "spacex-launches-db"
    }

    @Provides
    @Singleton
    fun provideLaunchDao(spaceXDatabase: SpaceXDatabase): SpaceXLaunchDao = spaceXDatabase.spaceXLaunchDao()

    @Provides
    @Singleton
    fun provideRocketDao(spaceXDatabase: SpaceXDatabase): SpaceXRocketDao = spaceXDatabase.spaceXRocketDao()

    @Provides
    @Singleton
    fun provideSpaceXDatabase(context: Context): SpaceXDatabase {
        return Room.databaseBuilder(context, SpaceXDatabase::class.java, DB_NAME).build()
    }
}