package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import com.scrpn.spacex.spacexrocketlaunches.domain.interactor.SpaceXApiInteractor
import hu.autsoft.rainbowcake.withIOContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val spaceXApiInteractor: SpaceXApiInteractor
) {

    suspend fun getRocket(id: Int): RocketDetail? = withIOContext {
        spaceXApiInteractor.getRocketById(id)?.let {
            RocketDetail(
                rocketId = it.rocketId!!,
                title = it.rocketName!!,
                description = it.description!!,
                flickrImageUrl = it.flickrImageUrl
            )
        }
    }

    suspend fun getLaunches(): List<LaunchPreview> = withIOContext {

        val launches = spaceXApiInteractor.getRocketLaunches()
            ?: return@withIOContext emptyList<LaunchPreview>()

        launches.map {
            val convertedDate = Date(TimeUnit.SECONDS.toMillis(it.launchDateUnix!!))
            val cal = Calendar.getInstance()
            cal.time = convertedDate
            val year = cal.get(Calendar.YEAR)

            LaunchPreview(
                flightNumber = it.flightNumber!!,
                missionName = it.missionName!!,
                date = convertedDate,
                year = year,
                launchSuccess = it.launchSuccess!!,
                missionPatch = it.missionPatch
            )
        }
    }

    suspend fun refreshLaunches(rocketId: String) = withIOContext {
        spaceXApiInteractor.refreshRocketLaunches(rocketId)
    }

    data class RocketDetail(
        val rocketId: String,
        val title: String,
        val description: String,
        val flickrImageUrl: String?
    )

    data class LaunchPreview (
        val flightNumber: Int,
        val missionName: String,
        val date: Date,
        val year: Int,
        val launchSuccess: Boolean,
        val missionPatch: String?
    )
}