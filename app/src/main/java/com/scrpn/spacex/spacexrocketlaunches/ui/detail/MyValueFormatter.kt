package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.DecimalFormat

class MyValueFormatter : IValueFormatter {
    private val mFormat = DecimalFormat("0")

    override fun getFormattedValue(value: Float, entry: Entry, dataSetIndex: Int, viewPortHandler: ViewPortHandler
    ): String {
        return mFormat.format(value)
    }
}
