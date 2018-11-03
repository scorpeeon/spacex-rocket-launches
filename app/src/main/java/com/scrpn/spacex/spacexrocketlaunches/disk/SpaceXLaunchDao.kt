package com.scrpn.spacex.spacexrocketlaunches.disk

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXLaunch

@Dao
interface SpaceXLaunchDao {
    @Query("SELECT * FROM launches")
    fun getAllSpaceXLaunches(): List<RoomSpaceXLaunch>

//    @Query("SELECT * FROM launches WHERE flightNumber = :launchId")
//    fun getSpaceXLaunchById(launchId: Int): SpaceXLaunchDao?

//    @Query("SELECT * FROM launches WHERE rocketId = :rocketId")
//    fun getSpaceXLaunchByRocketId(rocketId: String): SpaceXLaunchDao?

    @Insert
    fun insertSpaceXLaunches(spaceXLaunches: List<RoomSpaceXLaunch>)

    @Query("DELETE FROM launches")
    fun removeAllSpaceXLaunches()

    @Query("DELETE FROM launches WHERE rocketId = :rocketId")
    fun removeSpaceXLaunchesForRocket(rocketId: String)
}