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
    fun provideLaunchDao(spaceXLaunchDatabase: SpaceXDatabase): SpaceXLaunchDao = spaceXLaunchDatabase.spaceXLaunchDao()

    @Provides
    @Singleton
    fun provideSpaceXDatabase(context: Context): SpaceXDatabase {
        return Room.databaseBuilder(context, SpaceXDatabase::class.java, DB_NAME).build()
    }
}