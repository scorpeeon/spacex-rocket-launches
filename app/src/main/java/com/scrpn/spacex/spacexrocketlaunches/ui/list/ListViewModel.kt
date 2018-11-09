package com.scrpn.spacex.spacexrocketlaunches.ui.list

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val listPresenter: ListPresenter
) : JobViewModel<ListViewState>(ListViewState()) {

    fun refreshRockets() {
        execute {
            viewState = viewState.copy(refreshing = true)
            listPresenter.refreshRockets()
            viewState = ListViewState(rocketPreviews = listPresenter.getRockets(), refreshing = false)
        }
    }
}