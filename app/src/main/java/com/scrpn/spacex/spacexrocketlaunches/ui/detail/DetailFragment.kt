package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.scrpn.spacex.spacexrocketlaunches.R
import hu.autsoft.rainbowcake.base.BaseFragment
import hu.autsoft.rainbowcake.base.getViewModelFromFactory
import hu.autsoft.rainbowcake.extensions.requireArguments
import hu.autsoft.rainbowcake.extensions.withArgs
import hu.autsoft.rainbowcake.navigation.navigator
import kotlinx.android.synthetic.main.fragment_detail.*

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
        //Glide.with(ivArticleImage).load(article.imageUrl).into(ivArticleImage)
    }

    inline fun Bundle.requireInt(key: String): Int {
        return if (containsKey(key)) getInt(key) else throw IllegalStateException("Bundle has no key $key")
    }

}