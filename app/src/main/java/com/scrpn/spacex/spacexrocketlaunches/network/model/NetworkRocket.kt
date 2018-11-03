package com.scrpn.spacex.spacexrocketlaunches.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRocket (
    @SerializedName("rocket_id")
    val rocketId: String
)