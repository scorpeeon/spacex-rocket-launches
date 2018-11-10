package com.scrpn.spacex.spacexrocketlaunches.domain.model

data class SpaceXRocket (
    val id: Int?,
    val active: Boolean?,
    val stages: Int?,
    val boosters: Int?,
    val costPerLaunch: Int?,
    val successRatePct: Int?,
    val firstFlight: String?,
    val country: String?,
    val company: String?,
    val engineNumber: Int?,
    val wikipedia: String?,
    val description: String?,
    val rocketId: String?,
    val rocketName: String?,
    val rocketType: String?
)