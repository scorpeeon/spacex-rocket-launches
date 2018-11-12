package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.scrpn.spacex.spacexrocketlaunches.R
import kotlinx.android.synthetic.main.launch_list_content.view.*
import kotlinx.android.synthetic.main.launch_list_header.view.*
import java.text.DateFormat
import java.util.*


class LaunchPreviewAdapter(var context: Context?) : ListAdapter<Any, RecyclerView.ViewHolder>(LaunchPreviewComparator) {
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_LAUNCH = 1
    }

    var listener: Listener? = null
    var dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_list_header, parent, false)
            viewHolder = HeaderViewHolder(view)

        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_list_content, parent, false)
            viewHolder = ContentViewHolder(view)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_HEADER) {
            val item = getItem(position) as String

            val holder = holder as HeaderViewHolder

            holder.launchHeader.text = item
        } else {
            val item = getItem(position) as DetailPresenter.LaunchPreview

            val holder = holder as ContentViewHolder
            holder.item = item

            holder.missionName.text = item.missionName
            holder.launchDate.text = dateFormat.format(item.date)
            holder.launchSuccess.isChecked = item.launchSuccess
            Glide.with(holder.missionPatch).load(item.missionPatch).into(holder.missionPatch)
        }

    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val missionName = itemView.missionName
        val launchDate = itemView.launchDate
        val launchSuccess = itemView.launchSuccess
        val missionPatch = itemView.missionPatch

        var item: DetailPresenter.LaunchPreview? = null

        init {
            itemView.setOnClickListener {
                item?.let { item -> listener?.onItemSelected(item.flightNumber) }
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val launchHeader = itemView.launchHeader
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is String) {
            TYPE_HEADER
        } else {
            TYPE_LAUNCH
        }
    }

    interface Listener {
        fun onItemSelected(flightNumber: Int)
    }

    override fun submitList(list: List<Any>?) {
        // Adding headers for years
        val list = list as List<DetailPresenter.LaunchPreview>?
        val orderedList = list?.sortedBy { it.date }

        var listWithHeaders = ArrayList<Any>()
        orderedList?.forEach {
            val lastAddedElement = listWithHeaders.lastOrNull()
            if (lastAddedElement == null ||
                lastAddedElement is DetailPresenter.LaunchPreview && lastAddedElement.year != it.year) {
                listWithHeaders.add(it.year.toString())
            }
            listWithHeaders.add(it)
        }
        super.submitList(listWithHeaders)
    }
}
