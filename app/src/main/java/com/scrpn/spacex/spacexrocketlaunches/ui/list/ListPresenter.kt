package com.scrpn.spacex.spacexrocketlaunches.ui.list

import com.scrpn.spacex.spacexrocketlaunches.domain.interactor.SpaceXLaunchInteractor
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val spaceXLaunchInteractor: SpaceXLaunchInteractor
) {
    suspend fun getRockets(): List<RocketPreview> = withContext(Dispatchers.IO) {
        val articles = spaceXLaunchInteractor.getRocketLaunches()
            ?: return@withContext emptyList<RocketPreview>()

        articles.map {
            RocketPreview(

                id = it.rocketId!!
            )
        }
    }

    suspend fun refreshRockets() = withContext(Dispatchers.IO) {
        spaceXLaunchInteractor.refreshRocketLaunches("falconheavy")
    }

    data class RocketPreview(
        val id: String
    )
}