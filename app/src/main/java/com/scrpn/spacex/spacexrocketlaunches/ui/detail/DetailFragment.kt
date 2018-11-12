package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.scrpn.spacex.spacexrocketlaunches.R
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.extensions.requireArguments
import hu.autsoft.rainbowcake.extensions.withArgs
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

class DetailFragment : BaseFragment<DetailViewState, DetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    override fun getViewResource() = R.layout.fragment_detail

    companion object {
        private const val ROCKETS_ID = "ROCKETS_ID"

        fun newInstance(rocketsId: Int): DetailFragment {
            return DetailFragment().withArgs {
                putInt(ROCKETS_ID, rocketsId)
            }
        }
    }

    private var rocketsId: Int = 0

    private fun initArguments() {
        rocketsId = requireArguments().requireInt(ROCKETS_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArguments()
        setupToolbar()
        setupSwipeToRefreshLayout()
        setupChart()
    }

    private fun setupSwipeToRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.load(rocketsId)
        }
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            navigator?.pop()
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load(rocketsId)
    }

    override fun render(viewState: DetailViewState) {
        toolbar.title = viewState.rocketDetail?.title
        rocketDescriptionText.text = viewState.rocketDetail?.description
        swipeRefreshLayout.isRefreshing = viewState.refreshing
        Glide.with(toolbarImage).load(viewState.rocketDetail?.flickrImageUrl).into(toolbarImage)

        updateChart(viewState)
    }

    private fun updateChart(viewState: DetailViewState) {
        val barEntries: ArrayList<BarEntry> = ArrayList()
        val groupedLaunches = viewState.launchPreviews.groupBy { it.year }
        for (groupedLaunch in groupedLaunches) {
            barEntries.add(BarEntry(groupedLaunch.key.toFloat(), groupedLaunch.value.count().toFloat()))
        }

        val barDataSet = BarDataSet(barEntries, getString(R.string.launches))
        barDataSet.valueFormatter = MyValueFormatter()
        val barData = BarData(barDataSet)
        chart.data = barData

        chart.invalidate()
    }

    private fun setupChart() {
        val description = Description()
        description.text = getString(R.string.launches_per_year)
        chart.description = description

        chart.xAxis.granularity = 1.0f
        chart.xAxis.valueFormatter = MyYAxisValueFormatter()

        chart.axisLeft.granularity = 1.0f
        chart.axisLeft.axisMinimum = 0.0f

        chart.axisRight.granularity = 1.0f
        chart.axisRight.axisMinimum = 0.0f
    }

    inline fun Bundle.requireInt(key: String): Int {
        return if (containsKey(key)) getInt(key) else throw IllegalStateException("Bundle has no key $key")
    }

}