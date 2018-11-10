package com.scrpn.spacex.spacexrocketlaunches.network

import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkSpaceXLaunch
import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkSpaceXRocket
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {
    @GET("/v3/launches")
    fun getLaunches(@Query("rocket_id") rocketId: String): Deferred<List<NetworkSpaceXLaunch>>

    @GET("/v3/rockets")
    fun getRockets(): Deferred<List<NetworkSpaceXRocket>>
}