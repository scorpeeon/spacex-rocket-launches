package com.scrpn.spacex.spacexrocketlaunches.ui.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val listPresenter: ListPresenter
) : ViewModel() {

    private val _state = MutableLiveData<ListViewState>()
    protected var viewState: ListViewState
        get() = _state.value!!
        set(value) {
            _state.value = value
        }

    fun refreshRockets() {
        launch {
            viewState = viewState.copy(refreshing = true)
            listPresenter.refreshRockets()
            viewState = ListViewState(rocketPreviews = listPresenter.getRockets(), refreshing = false)
        }
    }
}