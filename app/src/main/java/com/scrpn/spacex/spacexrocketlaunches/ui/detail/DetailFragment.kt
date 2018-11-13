package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.scrpn.spacex.spacexrocketlaunches.R
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.extensions.requireArguments
import hu.autsoft.rainbowcake.extensions.withArgs
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

class DetailFragment : BaseFragment<DetailViewState, DetailViewModel>(), LaunchPreviewAdapter.Listener {
    override fun onItemSelected(flightNumber: Int) {
        // Doing nothing
    }

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_detail

    lateinit var adapter: LaunchPreviewAdapter

    companion object {
        private const val ROCKETS_ID = "ROCKETS_ID"

        private const val CHART_LINE_WIDTH = 2.0f

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
        setupRecyclerView()
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
        var activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupRecyclerView() {
        adapter = LaunchPreviewAdapter(context)
        adapter.listener = this
        launchList.adapter = adapter
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
        adapter.submitList(viewState.launchPreviews)
    }

    private fun updateChart(viewState: DetailViewState) {
        val lineEntries: ArrayList<Entry> = ArrayList()
        val groupedLaunches = viewState.launchPreviews.groupBy { it.year }
        for (groupedLaunch in groupedLaunches) {
            lineEntries.add(Entry(groupedLaunch.key.toFloat(), groupedLaunch.value.count().toFloat()))
        }

        if (!lineEntries.isEmpty()) {
            val lineDataSet = LineDataSet(lineEntries, getString(R.string.launches))
            lineDataSet.lineWidth = CHART_LINE_WIDTH
            lineDataSet.valueFormatter = MyValueFormatter()
            val lineData = LineData(lineDataSet)
            chart.data = lineData

            chart.invalidate()
        } else {
            chart.clear()
        }
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