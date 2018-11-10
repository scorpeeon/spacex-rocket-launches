package com.scrpn.spacex.spacexrocketlaunches.disk.model

import com.scrpn.spacex.spacexrocketlaunches.disk.*
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXRocket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiskDataSource @Inject constructor(
    private val spaceXLaunchDao: SpaceXLaunchDao,
    private val spaceXRocketDao: SpaceXRocketDao
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

    fun getAllSpaceXRockets(): List<SpaceXRocket>? {
        return spaceXRocketDao.getAllSpaceXRockets().map(RoomSpaceXRocket::toDomainSpaceXRocket)
    }

    fun insertSpaceXRockets(spaceXRockets: List<SpaceXRocket>) {
        val roomRockets = spaceXRockets.map(SpaceXRocket::toRoomSpaceXRocket)
        spaceXRocketDao.insertSpaceXRockets(roomRockets)
    }

    fun removeAllSpaceXRockets() {
        spaceXRocketDao.removeAllSpaceXRockets()
    }

//    fun getSpaceXLaunchById(launchId: Int): SpaceXLaunch? {
//        return spaceXLaunchDao.getSpaceXLaunchById(launchId)?.let(RoomSpaceXLaunch::toDomainSpaceXLaunch)
//    }
}