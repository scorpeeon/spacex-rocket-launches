package com.scrpn.spacex.spacexrocketlaunches.network.model

import com.google.gson.annotations.SerializedName

data class NetworkSpaceXRocket (
    val id: Int,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("success_rate_pct")
    val successRatePct: Int,
    @SerializedName("first_flight")
    val firstFlight: String,
    val country: String,
    val company: String,
    val engines: NetworkEngines,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    val wikipedia: String,
    val description: String,
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String
)
