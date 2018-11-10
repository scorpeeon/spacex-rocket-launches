package com.scrpn.spacex.spacexrocketlaunches.network.model

import com.google.gson.annotations.SerializedName

data class NetworkLinks (
    @SerializedName("mission_patch")
    val missionPatch: String
)
