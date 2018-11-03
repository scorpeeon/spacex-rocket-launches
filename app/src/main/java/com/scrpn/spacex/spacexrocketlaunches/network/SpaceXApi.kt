package com.scrpn.spacex.spacexrocketlaunches.network

import com.scrpn.spacex.spacexrocketlaunches.network.model.NetworkReply
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {
    @GET("/v3/launches")
    fun getLaunches(@Query("rocket_id") rocketId: String): Deferred<NetworkReply>
}