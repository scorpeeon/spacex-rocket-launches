package com.scrpn.spacex.spacexrocketlaunches.disk

import com.scrpn.spacex.spacexrocketlaunches.disk.model.RoomSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.domain.model.SpaceXLaunch

fun RoomSpaceXLaunch.toDomainSpaceXLaunch() = SpaceXLaunch(
    id = id,
    flightNumber = flightNumber,
    missionName = missionName,
    rocketId = rocketId,
    details = details,
    launchSuccess = launchSuccess
)

fun SpaceXLaunch.toRoomSpaceXLaunch() = RoomSpaceXLaunch(
    id = id,
    flightNumber = flightNumber,
    missionName = missionName,
    rocketId = rocketId,
    details = details,
    launchSuccess = launchSuccess
)