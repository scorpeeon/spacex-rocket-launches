package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import hu.autsoft.rainbowcake.base.JobViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailPresenter: DetailPresenter
) : JobViewModel<DetailViewState>(DetailViewState()) {
    fun load(id: Int) {
        execute {
            viewState = viewState.copy(rocketDetail = detailPresenter.getRocket(id), refreshing = true)
            detailPresenter.refreshLaunches(viewState.rocketDetail?.rocketId!!) // TODO viewState.rocketDetail?.rocketId!!
            viewState = viewState.copy(launchPreviews = detailPresenter.getLaunches(), refreshing = false)
        }
    }
}