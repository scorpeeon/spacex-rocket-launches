package com.scrpn.spacex.spacexrocketlaunches

import android.app.Application
import android.content.Context
import android.support.annotation.CallSuper
import android.support.multidex.MultiDex

open class SpaceXLaunchesApplication : Application() {
    lateinit var injector: AppComponent

    protected fun setupInjector() {
        injector = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    @CallSuper
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }
}