package com.scrpn.spacex.spacexrocketlaunches

import com.scrpn.spacex.spacexrocketlaunches.disk.DiskModule
import com.scrpn.spacex.spacexrocketlaunches.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class,
    DiskModule::class
])
interface AppComponent {
    // TODO
//    fun inject(baseActivity: BaseActivity)
//
//    fun inject(baseFragment: BaseFragment)
}