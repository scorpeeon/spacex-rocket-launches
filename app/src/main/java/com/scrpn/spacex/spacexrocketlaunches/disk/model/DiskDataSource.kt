package com.scrpn.spacex.spacexrocketlaunches.disk.model

import com.scrpn.spacex.spacexrocketlaunches.disk.SpaceXLaunchDao
import com.scrpn.spacex.spacexrocketlaunches.disk.toDomainSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.disk.toRoomSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiskDataSource @Inject constructor(
    private val spaceXLaunchDao: SpaceXLaunchDao
) {
    fun getAllSpaceXLaunches(): List<SpaceXLaunch> {
        return spaceXLaunchDao.getAllSpaceXLaunches().map(RoomSpaceXLaunch::toDomainSpaceXLaunch)
    }

    fun insertSpaceXLaunches(spaceXLaunches: List<SpaceXLaunch>) {
        val roomLaunches = spaceXLaunches.map(SpaceXLaunch::toRoomSpaceXLaunch)
        spaceXLaunchDao.insertSpaceXLaunches(roomLaunches)
    }

    fun removeAllSpaceXLaunches() {
        spaceXLaunchDao.removeAllSpaceXLaunches()
    }

    fun removeSpaceXLaunchesForRocket(rocketId: String) {
        spaceXLaunchDao.removeAllSpaceXLaunches()
    }

//    fun getSpaceXLaunchById(launchId: Int): SpaceXLaunch? {
//        return spaceXLaunchDao.getSpaceXLaunchById(launchId)?.let(RoomSpaceXLaunch::toDomainSpaceXLaunch)
//    }
}