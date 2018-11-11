package com.scrpn.spacex.spacexrocketlaunches.ui.detail

data class DetailViewState (
    val launchPreviews: List<DetailPresenter.LaunchPreview> = emptyList(),
    val rocketDetail: DetailPresenter.RocketDetail? = null,
    val refreshing: Boolean = false
)