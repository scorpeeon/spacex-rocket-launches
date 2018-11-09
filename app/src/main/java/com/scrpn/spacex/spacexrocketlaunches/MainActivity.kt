package com.scrpn.spacex.spacexrocketlaunches

import android.os.Bundle
import com.scrpn.spacex.spacexrocketlaunches.ui.list.ListFragment
import hu.autsoft.rainbowcake.navigation.SimpleNavActivity

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.add(ListFragment())
        }
    }

}