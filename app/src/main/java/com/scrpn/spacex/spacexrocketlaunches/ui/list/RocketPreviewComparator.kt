package com.scrpn.spacex.spacexrocketlaunches.ui.list

import android.support.v7.util.DiffUtil

object RocketPreviewComparator : DiffUtil.ItemCallback<ListPresenter.RocketPreview>() {

    override fun areItemsTheSame(oldItem: ListPresenter.RocketPreview, newItem: ListPresenter.RocketPreview): Boolean {
        return oldItem.rocketId == newItem.rocketId
    }

    override fun areContentsTheSame(oldItem: ListPresenter.RocketPreview, newItem: ListPresenter.RocketPreview): Boolean {
        return oldItem == newItem
    }

}
