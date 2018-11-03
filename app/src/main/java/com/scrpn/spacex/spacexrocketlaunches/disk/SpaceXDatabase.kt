package com.scrpn.spacex.spacexrocketlaunches.disk

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXLaunch

@Database(entities = [RoomSpaceXLaunch::class], version = 1)
abstract class SpaceXDatabase : RoomDatabase() {

    abstract fun spaceXLaunchDao(): SpaceXLaunchDao

}