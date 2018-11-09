package com.scrpn.spacex.spacexrocketlaunches.di

import com.scrpn.spacex.spacexrocketlaunches.disk.DiskModule
import com.scrpn.spacex.spacexrocketlaunches.network.NetworkModule
import dagger.Component
import hu.autsoft.rainbowcake.di.BaseComponent
import hu.autsoft.rainbowcake.di.BaseModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    ApplicationModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    DiskModule::class
])
interface AppComponent : BaseComponent