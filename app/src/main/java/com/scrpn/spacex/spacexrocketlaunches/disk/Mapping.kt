package com.scrpn.spacex.spacexrocketlaunches.disk

import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXRocket
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXRocket

fun RoomSpaceXLaunch.toDomainSpaceXLaunch() = SpaceXLaunch(
    id = id,
    flightNumber = flightNumber,
    missionName = missionName,
    launchDateUnix = launchDateUnix,
    rocketId = rocketId,
    details = details,
    launchSuccess = launchSuccess,
    missionPatch = missionPatch
)

fun SpaceXLaunch.toRoomSpaceXLaunch() = RoomSpaceXLaunch(
    id = id,
    flightNumber = flightNumber,
    missionName = missionName,
    launchDateUnix = launchDateUnix,
    rocketId = rocketId,
    details = details,
    launchSuccess = launchSuccess,
    missionPatch = missionPatch
)

fun RoomSpaceXRocket.toDomainSpaceXRocket() = SpaceXRocket(
    id = id,
    active = active,
    stages = stages,
    boosters = boosters,
    costPerLaunch = costPerLaunch,
    successRatePct = successRatePct,
    firstFlight = firstFlight,
    country = country,
    company = company,
    engineNumber = engineNumber,
    wikipedia = wikipedia,
    description = description,
    rocketId = rocketId,
    rocketName = rocketName,
    rocketType = rocketType
)

fun SpaceXRocket.toRoomSpaceXRocket() = RoomSpaceXRocket(
    id = id,
    active = active,
    stages = stages,
    boosters = boosters,
    costPerLaunch = costPerLaunch,
    successRatePct = successRatePct,
    firstFlight = firstFlight,
    country = country,
    company = company,
    engineNumber = engineNumber,
    wikipedia = wikipedia,
    description = description,
    rocketId = rocketId,
    rocketName = rocketName,
    rocketType = rocketType
)