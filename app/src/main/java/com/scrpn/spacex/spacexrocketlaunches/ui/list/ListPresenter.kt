package com.scrpn.spacex.spacexrocketlaunches.ui.list

import com.scrpn.spacex.spacexrocketlaunches.domain.interactor.SpaceXApiInteractor
import hu.autsoft.rainbowcake.withIOContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val spaceXApiInteractor: SpaceXApiInteractor
) {
    suspend fun getRockets(): List<RocketPreview> = withIOContext {
        val rockets = spaceXApiInteractor.getRockets()
            ?: return@withIOContext emptyList<RocketPreview>()

        val launches = spaceXApiInteractor.getRocketLaunches()
            ?: return@withIOContext emptyList<RocketPreview>() // TODO delete
        var date = Date(TimeUnit.SECONDS.toMillis(launches[0].launchDateUnix!!))

        rockets.map {
            RocketPreview(

                rocketId = it.rocketId!!,
                rocketName = it.rocketName!!,
                country = it.country!!,
                engineNumber = it.engineNumber!!
            )
        }
    }

    suspend fun refreshRockets() = withIOContext {
        spaceXApiInteractor.refreshRocketLaunches("falconheavy") // TODO delete
        spaceXApiInteractor.refreshRockets()
    }

    data class RocketPreview(
        val rocketId: String,
        val rocketName: String,
        val country: String,
        val engineNumber: Int
    )
}