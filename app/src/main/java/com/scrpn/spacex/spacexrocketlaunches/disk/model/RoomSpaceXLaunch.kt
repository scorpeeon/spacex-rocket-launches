package com.scrpn.spacex.spacexrocketlaunches.disk.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "launches")
data class RoomSpaceXLaunch (
    var id: String? = null,
    @PrimaryKey
    var flightNumber: Int? = null,
    var missionName: String? = null,
    var launchDateUnix: Long? = null,
    var rocketId: String? = null,
    var details: String? = null,
    var launchSuccess: Boolean? = null,
    var missionPatch: String?
)