package com.scrpn.spacex.spacexrocketlaunches.ui.list

data class ListViewState (
    val rocketPreviews: List<ListPresenter.RocketPreview> = emptyList(),
    val refreshing: Boolean = false,
    val filterByActive: Boolean = false
)