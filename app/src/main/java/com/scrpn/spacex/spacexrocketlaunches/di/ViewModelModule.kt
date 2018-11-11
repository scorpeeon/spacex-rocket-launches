package com.scrpn.spacex.spacexrocketlaunches.di

import android.arch.lifecycle.ViewModel
import com.scrpn.spacex.spacexrocketlaunches.ui.detail.DetailViewModel
import com.scrpn.spacex.spacexrocketlaunches.ui.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import hu.autsoft.rainbowcake.di.ViewModelKey

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

}
