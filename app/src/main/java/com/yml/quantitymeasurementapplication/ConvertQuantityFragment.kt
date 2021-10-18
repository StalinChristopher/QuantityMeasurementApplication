package com.yml.quantitymeasurementapplication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.yml.quantitymeasurementapplication.util.ConvertValues
import com.yml.quantitymeasurementapplication.util.DesiredMetric.desiredMetric
import org.w3c.dom.Text

class ConvertQuantityFragment : Fragment(R.layout.convert_quantity) {
    lateinit var topLeftSpinner : Spinner
    lateinit var bottomLeftSpinner : Spinner
    lateinit var bottomRightSpinner : Spinner
    lateinit var userInput : EditText
    lateinit var resultValue : EditText
    companion object{
        var selectedMetric : String = "Length"
        var firstUnit = ""
        var secondUnit = ""
        var output = 0.0
        var input = 0.0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topLeftSpinner = view.findViewById<Spinner>(R.id.topLeftSpinnerConvert)
        bottomLeftSpinner = view.findViewById<Spinner>(R.id.bottomLeftSpinnerConvert)
        bottomRightSpinner = view.findViewById<Spinner>(R.id.bottomRightSpinnerConvert)
        userInput = view.findViewById<EditText>(R.id.firstValueConvert)
        resultValue = view.findViewById(R.id.secondValueConvert)
        resultValue.setText("")

        var arrayAdapter = ArrayAdapter.createFromResource(requireContext(),R.array.metrics,android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        topLeftSpinner.adapter = arrayAdapter

        allListeners()

    }

    private fun allListeners() {
        topLeftSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedMetric = topLeftSpinner.selectedItem.toString()
                var unitMetricAdapter = desiredMetric(requireContext(),selectedMetric)
                bottomLeftSpinner.adapter = unitMetricAdapter
                bottomRightSpinner.adapter = unitMetricAdapter
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedMetric = "Length"
                var unitMetricAdapter = desiredMetric(requireContext(),selectedMetric)
                bottomLeftSpinner.adapter = unitMetricAdapter
                bottomRightSpinner.adapter = unitMetricAdapter
            }

        }

        bottomLeftSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                firstUnit = bottomLeftSpinner?.selectedItem.toString()
                selectAndConvertValues()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        bottomRightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                secondUnit = bottomRightSpinner?.selectedItem.toString()
                selectAndConvertValues()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        userInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(userInput.text.isNullOrEmpty()){
                    input = Double.MAX_VALUE
                    selectAndConvertValues()
                    return
                }
                input= userInput.text.toString().toDouble()
                selectAndConvertValues()

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun selectAndConvertValues() {
        if(input == Double.MAX_VALUE){
            output = 0.0
            resultValue.setText(output.toString())
            return
        }
        output = when (selectedMetric) {
            "Length" -> {
                ConvertValues.calculateLength(input, firstUnit, secondUnit)
            }
            "Weight" -> {
                ConvertValues.calculateWeight(input, firstUnit, secondUnit)
            }
            "Temperature" -> {
                ConvertValues.calculateTemperature(input, firstUnit, secondUnit)
            }
            "Volume" -> {
                ConvertValues.calculateVolume(input, firstUnit, secondUnit)
            }
            else -> 0.0
        }
        var outputFormatString = String.format("%.3f", output)
        resultValue.setText(outputFormatString)
    }

    override fun onStop() {
        super.onStop()
        resultValue.setText("")
    }

}



