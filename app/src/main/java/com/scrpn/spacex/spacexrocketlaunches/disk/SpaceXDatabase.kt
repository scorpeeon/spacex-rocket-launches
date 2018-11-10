package com.scrpn.spacex.spacexrocketlaunches.disk

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXRocket

@Database(entities = [RoomSpaceXLaunch::class, RoomSpaceXRocket::class], version = 1)
abstract class SpaceXDatabase : RoomDatabase() {

    abstract fun spaceXLaunchDao(): SpaceXLaunchDao

    abstract fun spaceXRocketDao(): SpaceXRocketDao

}