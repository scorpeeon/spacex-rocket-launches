package com.scrpn.spacex.spacexrocketlaunches.ui.list

import com.scrpn.spacex.spacexrocketlaunches.domain.interactor.SpaceXApiInteractor
import hu.autsoft.rainbowcake.withIOContext
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val spaceXApiInteractor: SpaceXApiInteractor
) {
    suspend fun getRockets(): List<RocketPreview> = withIOContext {
        val articles = spaceXApiInteractor.getRockets()
            ?: return@withIOContext emptyList<RocketPreview>()

        articles.map {
            RocketPreview(

                rocketId = it.rocketId!!,
                rocketName = it.rocketName!!,
                country = it.country!!,
                engineNumber = it.engineNumber!!
            )
        }
    }

    suspend fun refreshRockets() = withIOContext {
        spaceXApiInteractor.refreshRockets()
    }

    data class RocketPreview(
        val rocketId: String,
        val rocketName: String,
        val country: String,
        val engineNumber: Int
    )
}