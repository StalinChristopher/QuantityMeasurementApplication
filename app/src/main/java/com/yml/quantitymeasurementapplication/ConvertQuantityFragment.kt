package com.yml.quantitymeasurementapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.yml.quantitymeasurementapplication.util.DesiredMetric.desiredMetric

class ConvertQuantityFragment : Fragment(R.layout.convert_quantity) {
    lateinit var selectedMetric : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topLeftSpinner= view.findViewById<Spinner>(R.id.topLeftSpinnerConvert)
        var arrayAdapter = ArrayAdapter.createFromResource(requireContext(),R.array.metrics,android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        topLeftSpinner.adapter = arrayAdapter
        topLeftSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedMetric = topLeftSpinner.selectedItem.toString()
                metricsUnit(selectedMetric,view)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    fun metricsUnit(selectedMetric : String, view: View) {
        var unitMetricAdapter = desiredMetric(requireContext(),selectedMetric)
        var bottomLeftSpinner = view.findViewById<Spinner>(R.id.bottomLeftSpinnerConvert)
        var bottomRightSpinner = view.findViewById<Spinner>(R.id.bottomRightSpinnerConvert)
        bottomLeftSpinner.adapter = unitMetricAdapter
        bottomRightSpinner.adapter = unitMetricAdapter
    }

}