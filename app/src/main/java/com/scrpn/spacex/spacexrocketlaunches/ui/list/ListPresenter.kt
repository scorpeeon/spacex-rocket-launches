package com.scrpn.spacex.spacexrocketlaunches.ui.list

import com.scrpn.spacex.spacexrocketlaunches.domain.interactor.SpaceXLaunchInteractor
import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val spaceXLaunchInteractor: SpaceXLaunchInteractor
) {
    suspend fun getRockets(): List<RocketPreview> = withIOContext {
        val articles = spaceXLaunchInteractor.getRocketLaunches()
            ?: return@withIOContext emptyList<RocketPreview>()

        articles.map {
            RocketPreview(

                id = it.rocketId!!
            )
        }
    }

    suspend fun refreshRockets() = withIOContext {
        spaceXLaunchInteractor.refreshRocketLaunches("falconheavy")
    }

    data class RocketPreview(
        val id: String
    )
}