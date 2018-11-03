package com.scrpn.spacex.spacexrocketlaunches.domain.model

data class SpaceXLaunch (
    val id: String?,
    val flightNumber: Int?,
    var missionName: String?,
    var rocketId: String?,
    var details: String?,
    var launchSuccess: Boolean?
)