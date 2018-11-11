package com.scrpn.spacex.spacexrocketlaunches.ui.detail

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.DecimalFormat


class MyYAxisValueFormatter : IAxisValueFormatter {

    private val mFormat = DecimalFormat("0")

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        // "value" represents the position of the label on the axis (x or y)
        return mFormat.format(value)
    }
}