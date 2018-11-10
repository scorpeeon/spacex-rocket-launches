package com.scrpn.spacex.spacexrocketlaunches.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkSpaceXLaunch (
    @SerializedName("_id")
    val id: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("mission_name")
    val missionName: String?,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Long,
    val rocket: NetworkRocket,
    val details: String?,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    val links: NetworkLinks
)
