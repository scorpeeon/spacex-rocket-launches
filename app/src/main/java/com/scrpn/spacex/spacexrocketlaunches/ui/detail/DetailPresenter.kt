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
                description = it.description!!
            )
        }
    }

    suspend fun getLaunches(): List<LaunchPreview> = withIOContext {

        val launches = spaceXApiInteractor.getRocketLaunches()
            ?: return@withIOContext emptyList<LaunchPreview>()

        launches.map {
            LaunchPreview(
                missionName = it.missionName!!,
                date = Date(TimeUnit.SECONDS.toMillis(it.launchDateUnix!!)),
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
        val description: String
    )

    data class LaunchPreview (
        val missionName: String,
        val date: Date,
        val launchSuccess: Boolean,
        val missionPatch: String?
    )
}