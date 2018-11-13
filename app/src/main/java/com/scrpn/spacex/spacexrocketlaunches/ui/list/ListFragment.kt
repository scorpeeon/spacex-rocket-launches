package com.scrpn.spacex.spacexrocketlaunches.ui.list

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.view.View
import androidx.core.view.get
import com.scrpn.spacex.spacexrocketlaunches.R
import com.scrpn.spacex.spacexrocketlaunches.ui.detail.DetailFragment
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_list.*
import android.support.v7.app.AlertDialog


class ListFragment : BaseFragment<ListViewState, ListViewModel>(), RocketPreviewAdapter.Listener {

    companion object {
        private const val COMPLETED_ONBOARDING_PREF_NAME = "COMPLETED_ONBOARDING_PREF_NAME"
    }

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_list

    private lateinit var adapter: RocketPreviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()

        PreferenceManager.getDefaultSharedPreferences(context).apply {
            if (!getBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)) {
                showWelcomeDialog()
            }
        }
    }

    private fun showWelcomeDialog() {
        context?.let { context ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.welcome))
                .setMessage(getString(R.string.welcome_details))
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                        putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
                        apply()
                    }
                }
                .show()
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshRockets()
    }

    override fun render(viewState: ListViewState) {
        var listToDisplay = viewState.rocketPreviews
        if (viewState.filterByActive) {
            listToDisplay = listToDisplay.filter { it.active }
        }
        adapter.submitList(listToDisplay)

        toolbar.menu[0].isChecked = viewState.filterByActive

        swipeRefreshLayout.isRefreshing = viewState.refreshing
    }

    private fun setupRecyclerView() {
        adapter = RocketPreviewAdapter(context)
        adapter.listener = this
        rocketList.adapter = adapter

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshRockets()
        }
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.menu_list)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter_by_active -> {
                    it.isChecked = !it.isChecked
                    viewModel.setFilterByActive(it.isChecked)
                }
            }
            true
        }
    }

    override fun onItemSelected(rocketsId: Int) {
        navigator?.add(DetailFragment.newInstance(rocketsId))
    }
}