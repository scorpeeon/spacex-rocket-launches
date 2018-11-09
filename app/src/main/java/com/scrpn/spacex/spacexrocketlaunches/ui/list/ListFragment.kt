package com.scrpn.spacex.spacexrocketlaunches.ui.list

import com.scrpn.spacex.spacexrocketlaunches.R
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory

class ListFragment : BaseFragment<ListViewState, ListViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.main_fragment // TODO

    override fun render(viewState: ListViewState) {
        // TODO
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshRockets()
    }
}