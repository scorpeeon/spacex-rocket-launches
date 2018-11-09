package com.scrpn.spacex.spacexrocketlaunches

import com.scrpn.spacex.spacexrocketlaunches.di.AppComponent
import com.scrpn.spacex.spacexrocketlaunches.di.ApplicationModule
import com.scrpn.spacex.spacexrocketlaunches.di.DaggerAppComponent
import hu.autsoft.rainbowcake.BaseApplication

open class SpaceXLaunchesApplication : BaseApplication() {
    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}