package com.scrpn.spacex.spacexrocketlaunches.domain.interactor

import com.scrpn.spacex.spacexrocketlaunches.disk.model.DiskDataSource
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXRocket
import com.scrpn.spacex.spacexrocketlaunches.network.NetworkDataSource
import javax.inject.Inject

class SpaceXApiInteractor @Inject constructor(
    private val diskDataSource: DiskDataSource,
    private val networkDataSource: NetworkDataSource
) {

    fun getRocketLaunches(): List<SpaceXLaunch>? {
        return diskDataSource.getAllSpaceXLaunches()
    }

    fun getRocketLaunchesForRocket(rocketId: String): List<SpaceXLaunch>? {
        return diskDataSource.getSpaceXLaunchesForRocket(rocketId)
    }

    suspend fun refreshRocketLaunches(rocketId: String) {
        val launches = networkDataSource.getLaunches(rocketId) ?: return

        diskDataSource.removeSpaceXLaunchesForRocket(rocketId)
        diskDataSource.insertSpaceXLaunches(launches)
    }

    fun getRockets(): List<SpaceXRocket>? {
        return diskDataSource.getAllSpaceXRockets()
    }

    fun getRocketById(id: Int): SpaceXRocket? {
        return diskDataSource.getSpaceXRocketById(id)
    }

    suspend fun refreshRockets() {
        val rockets = networkDataSource.getRockets() ?: return

        diskDataSource.removeAllSpaceXRockets()
        diskDataSource.insertSpaceXRockets(rockets)
    }
}