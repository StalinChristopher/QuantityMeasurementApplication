package com.yml.quantitymeasurementapplication.util

import android.content.Context
import com.yml.quantitymeasurementapplication.R
import android.widget.ArrayAdapter

object DesiredMetric {
    fun desiredMetric(context : Context, selectedMetric : String) : ArrayAdapter<CharSequence> {
        var metricUnitArray = when(selectedMetric){
            "Length" -> { R.array.lengthMetrics}
            "Weight" -> {R.array.weightMetrics}
            "Temperature" -> {R.array.temperatureMetrics}
            "Volume" -> {R.array.volumeMetrics}
            else -> {R.array.lengthMetrics}
        }
        var arrayAdapter = ArrayAdapter.createFromResource(context, metricUnitArray!!, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return arrayAdapter

    }
}