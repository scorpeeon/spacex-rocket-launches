package com.scrpn.spacex.spacexrocketlaunches.ui.list

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.scrpn.spacex.spacexrocketlaunches.R
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment<ListViewState, ListViewModel>(), RocketPreviewAdapter.Listener {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_list

    lateinit var adapter: RocketPreviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshRockets()
    }

    override fun render(viewState: ListViewState) {
        adapter.submitList(viewState.rocketPreviews)

        swipeRefreshLayout.isRefreshing = viewState.refreshing
    }

    private fun setupRecyclerView() {
        adapter = RocketPreviewAdapter()
        adapter.listener = this
        articleList.adapter = adapter

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshRockets()
        }
    }

    private fun setupToolbar() {
        // toolbar.inflateMenu(R.menu.menu_list) // TODO
    }

    override fun onItemSelected(rocketId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}