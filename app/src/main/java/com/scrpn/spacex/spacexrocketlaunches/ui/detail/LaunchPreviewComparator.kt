package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.support.v7.util.DiffUtil

object LaunchPreviewComparator : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is DetailPresenter.LaunchPreview && newItem is DetailPresenter.LaunchPreview) {
            oldItem.flightNumber == newItem.flightNumber
        } else if (oldItem is String && newItem is String) {
            oldItem == newItem
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

}
