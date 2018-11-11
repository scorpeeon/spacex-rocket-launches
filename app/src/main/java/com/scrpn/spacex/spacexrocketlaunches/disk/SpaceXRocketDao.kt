package com.scrpn.spacex.spacexrocketlaunches.disk

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXRocket

@Dao
interface SpaceXRocketDao {
    @Query("SELECT * FROM rockets")
    fun getAllSpaceXRockets(): List<RoomSpaceXRocket>

    @Query("SELECT * FROM rockets WHERE id = :id")
    fun getSpaceXRocketById(id: Int): RoomSpaceXRocket?

    @Insert
    fun insertSpaceXRockets(spaceXRockets: List<RoomSpaceXRocket>)

    @Query("DELETE FROM rockets")
    fun removeAllSpaceXRockets()
}