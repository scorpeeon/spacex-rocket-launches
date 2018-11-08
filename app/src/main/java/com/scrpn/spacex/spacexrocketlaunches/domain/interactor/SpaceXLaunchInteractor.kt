package com.scrpn.spacex.spacexrocketlaunches.domain.interactor

import com.scrpn.spacex.spacexrocketlaunches.disk.model.DiskDataSource
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.network.NetworkDataSource
import javax.inject.Inject

class SpaceXLaunchInteractor @Inject constructor(
    private val diskDataSource: DiskDataSource,
    private val networkDataSource: NetworkDataSource
) {

    fun getRocketLaunches(): List<SpaceXLaunch>? {
        return diskDataSource.getAllSpaceXLaunches()
    }

    suspend fun refreshRocketLaunches(rocketId: String) {
        val launches = networkDataSource.getLaunches(rocketId) ?: return

        //diskDataSource.removeAllSpaceXLaunches()
        diskDataSource.removeSpaceXLaunchesForRocket(rocketId)
        diskDataSource.insertSpaceXLaunches(launches)
    }
}