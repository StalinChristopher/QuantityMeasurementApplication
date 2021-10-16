package com.yml.quantitymeasurementapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.yml.quantitymeasurementapplication.util.DesiredMetric

class AddQuantityFragment : Fragment(R.layout.add_quantity) {
    lateinit var selectedMetric : String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topLeftSpinner= view.findViewById<Spinner>(R.id.topLeftSpinnerAdd)
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
        var unitMetricAdapter = DesiredMetric.desiredMetric(requireContext(), selectedMetric)
        var bottomSpinner1 = view.findViewById<Spinner>(R.id.bottomAddSpinner1)
        var bottomSpinner2 = view.findViewById<Spinner>(R.id.bottomAddSpinner2)
        var bottomSpinner3 = view.findViewById<Spinner>(R.id.bottomAddSpinner3)
        bottomSpinner1.adapter = unitMetricAdapter
        bottomSpinner2.adapter = unitMetricAdapter
        bottomSpinner3.adapter = unitMetricAdapter
    }
}