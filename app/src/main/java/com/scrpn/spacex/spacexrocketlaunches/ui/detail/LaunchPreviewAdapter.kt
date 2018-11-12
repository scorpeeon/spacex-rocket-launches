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
import java.text.DateFormat
import java.util.*
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy.SHORT



class LaunchPreviewAdapter(var context: Context?) : ListAdapter<DetailPresenter.LaunchPreview, LaunchPreviewAdapter.ViewHolder>(LaunchPreviewComparator) {
    var listener: Listener? = null
    var dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.launch_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.item = item

        holder.missionName.text = item.missionName
        holder.launchDate.text = dateFormat.format(item.date)
        holder.launchSuccess.isChecked = item.launchSuccess
        Glide.with(holder.missionPatch).load(item.missionPatch).into(holder.missionPatch)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    interface Listener {
        fun onItemSelected(flightNumber: Int)
    }
}
