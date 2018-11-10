package com.scrpn.spacex.spacexrocketlaunches.network

import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXRocket
import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkSpaceXRocket
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

        return reply.map { networkSpaceXLaunch: NetworkSpaceXLaunch ->

            SpaceXLaunch(
                flightNumber = networkSpaceXLaunch.flightNumber,
                missionName = networkSpaceXLaunch.missionName,
                id = networkSpaceXLaunch.id,
                launchDateUnix = networkSpaceXLaunch.launchDateUnix,
                rocketId = networkSpaceXLaunch.rocket.rocketId,
                details = networkSpaceXLaunch.details,
                launchSuccess = networkSpaceXLaunch.launchSuccess,
                missionPatch = networkSpaceXLaunch.links.missionPatch
            )
        }
    }

    suspend fun getRockets(): List<SpaceXRocket>? {
        val reply = try {
            spaceXApi.getRockets().await()
        } catch (e: IOException) {
            return null
        }

        return reply.map { networkSpaceXRocket: NetworkSpaceXRocket ->

            SpaceXRocket(
                id = networkSpaceXRocket.id,
                active = networkSpaceXRocket.active,
                stages = networkSpaceXRocket.stages,
                boosters = networkSpaceXRocket.boosters,
                costPerLaunch = networkSpaceXRocket.costPerLaunch,
                successRatePct = networkSpaceXRocket.successRatePct,
                firstFlight = networkSpaceXRocket.firstFlight,
                country = networkSpaceXRocket.country,
                company = networkSpaceXRocket.company,
                engineNumber = networkSpaceXRocket.engines.number,
                wikipedia = networkSpaceXRocket.wikipedia,
                description = networkSpaceXRocket.description,
                rocketId = networkSpaceXRocket.rocketId,
                rocketName = networkSpaceXRocket.rocketName,
                rocketType = networkSpaceXRocket.rocketType
            )
        }
    }
}