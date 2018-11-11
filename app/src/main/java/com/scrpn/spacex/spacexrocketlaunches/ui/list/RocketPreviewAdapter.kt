package com.scrpn.spacex.spacexrocketlaunches.ui.list

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scrpn.spacex.spacexrocketlaunches.R
import kotlinx.android.synthetic.main.rocket_list_content.view.*

class RocketPreviewAdapter(var context: Context?) : ListAdapter<ListPresenter.RocketPreview, RocketPreviewAdapter.ViewHolder>(RocketPreviewComparator) {
    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rocket_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.item = item

        holder.title.text = item.rocketName
        holder.details.text = item.country
        holder.engineNumber.text = context?.getString(R.string.engine_number, item.engineNumber)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val details = itemView.details
        val engineNumber = itemView.engineNumber

        var item: ListPresenter.RocketPreview? = null

        init {
            itemView.setOnClickListener {
                item?.let { item -> listener?.onItemSelected(item.id) }
            }
        }
    }

    interface Listener {
        fun onItemSelected(rocketsId: Int)
    }
}
