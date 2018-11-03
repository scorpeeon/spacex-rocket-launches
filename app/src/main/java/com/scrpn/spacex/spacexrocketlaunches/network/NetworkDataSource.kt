package com.scrpn.spacex.spacexrocketlaunches.network

import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkSpaceXLaunch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val spaceXApi: SpaceXApi
) {
    suspend fun getLaunches(rocketId: String): List<SpaceXLaunch>? {
        val reply = try {
            spaceXApi.getLaunches(rocketId).await()
        } catch (e: IOException) {
            return null
        }

        return reply.results.map { networkSpaceXLaunch: NetworkSpaceXLaunch ->

            SpaceXLaunch(
                flightNumber = networkSpaceXLaunch.flightNumber,
                missionName = networkSpaceXLaunch.missionName,
                id = networkSpaceXLaunch.id,
                rocketId = networkSpaceXLaunch.rocket.rocketId,
                details = networkSpaceXLaunch.details,
                launchSuccess = networkSpaceXLaunch.launchSuccess
            )
        }
    }
}