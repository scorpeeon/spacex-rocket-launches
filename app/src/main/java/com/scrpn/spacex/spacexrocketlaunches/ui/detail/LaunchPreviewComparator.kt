package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.support.v7.util.DiffUtil

object LaunchPreviewComparator : DiffUtil.ItemCallback<DetailPresenter.LaunchPreview>() {

    override fun areItemsTheSame(oldItem: DetailPresenter.LaunchPreview, newItem: DetailPresenter.LaunchPreview): Boolean {
        return oldItem.flightNumber == newItem.flightNumber
    }

    override fun areContentsTheSame(oldItem:DetailPresenter.LaunchPreview, newItem: DetailPresenter.LaunchPreview): Boolean {
        return oldItem == newItem
    }

}
