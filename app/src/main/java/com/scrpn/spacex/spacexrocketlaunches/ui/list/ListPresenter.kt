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

        rockets.map {
            RocketPreview(

                id = it.id!!,
                rocketId = it.rocketId!!,
                rocketName = it.rocketName!!,
                country = it.country!!,
                engineNumber = it.engineNumber!!,
                active = it.active!!
            )
        }
    }

    suspend fun refreshRockets() = withIOContext {
        spaceXApiInteractor.refreshRockets()
    }

    data class RocketPreview(
        val id: Int,
        val rocketId: String,
        val rocketName: String,
        val country: String,
        val engineNumber: Int,
        val active: Boolean
    )
}