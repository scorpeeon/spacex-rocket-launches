package com.scrpn.spacex.spacexrocketlaunches.disk.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rockets")
data class RoomSpaceXRocket (
    @PrimaryKey
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
    val flickrImageUrl: String?,
    val wikipedia: String?,
    val description: String?,
    val rocketId: String?,
    val rocketName: String?,
    val rocketType: String?
)