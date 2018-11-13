package com.scrpn.spacex.spacexrocketlaunches

import android.os.Bundle
import android.view.MenuItem
import com.scrpn.spacex.spacexrocketlaunches.ui.list.ListFragment
import hu.autsoft.rainbowcake.navigation.SimpleNavActivity

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.add(ListFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}