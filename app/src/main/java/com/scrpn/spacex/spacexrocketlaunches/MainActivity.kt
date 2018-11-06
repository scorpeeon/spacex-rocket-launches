package com.scrpn.spacex.spacexrocketlaunches

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.scrpn.spacex.myapplicationviewmodel.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}