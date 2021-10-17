package com.yml.quantitymeasurementapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.yml.quantitymeasurementapplication.util.ConvertValues
import com.yml.quantitymeasurementapplication.util.DesiredMetric

class AddQuantityFragment : Fragment(R.layout.add_quantity) {
    lateinit var selectedMetric : String
    lateinit var topLeftSpinner : Spinner
    lateinit var bottomLeftSpinner : Spinner
    lateinit var bottomMiddleSpinner: Spinner
    lateinit var bottomRightSpinner: Spinner
    lateinit var userInput1 : EditText
    lateinit var userInput2 : EditText
    lateinit var resultOutput : EditText

    companion object{
        var selectedMetric : String = "Length"
        var firstUnit = "CENTIMETER"
        var secondUnit = "CENTIMETER"
        var resultUnit = "CENTIMETER"
        var output = 0.0
        var input1 = 0.0
        var input2 = 0.0
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomLeftSpinner = view.findViewById(R.id.bottomAddSpinner1)
        bottomMiddleSpinner = view.findViewById(R.id.bottomAddSpinner2)
        bottomRightSpinner = view.findViewById(R.id.bottomAddSpinner3)
        topLeftSpinner= view.findViewById<Spinner>(R.id.topLeftSpinnerAdd)
        userInput1 = view.findViewById(R.id.firstValueAdd)
        userInput2 = view.findViewById(R.id.secondValueAdd)
        resultOutput = view.findViewById(R.id.resultValueAdd)

        var arrayAdapter = ArrayAdapter.createFromResource(requireContext(),R.array.metrics,android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        topLeftSpinner.adapter = arrayAdapter
        allListeners()
    }

    private fun allListeners() {
        topLeftSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedMetric = topLeftSpinner.selectedItem.toString()
                metricsUnit(selectedMetric)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        bottomLeftSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                firstUnit = bottomLeftSpinner.selectedItem.toString()
                selectAndConvertValues()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        bottomMiddleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                secondUnit = bottomMiddleSpinner.selectedItem.toString()
                selectAndConvertValues()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        bottomRightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                resultUnit = bottomRightSpinner.selectedItem.toString()
                selectAndConvertValues()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        userInput1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.isNullOrEmpty()){
                    input1 =  0.0
                    selectAndConvertValues()
                    return
                }
                input1 = userInput1.text.toString().toDouble()
                selectAndConvertValues()
            }

            override fun afterTextChanged(p0: Editable?){}
        })

        userInput2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.isNullOrEmpty()){
                    input2 =  0.0
                    selectAndConvertValues()
                    return
                }
                input2 = userInput2.text.toString().toDouble()
                selectAndConvertValues()
            }

            override fun afterTextChanged(p0: Editable?){}
        })

    }

    private fun selectAndConvertValues() {
        output = ConvertValues.addMetricValues(selectedMetric, firstUnit, secondUnit, resultUnit,
            input1, input2)
        resultOutput.setText(output.toString())
    }

    fun metricsUnit(selectedMetric : String) {
        var unitMetricAdapter = DesiredMetric.desiredMetric(requireContext(), selectedMetric)
        bottomLeftSpinner.adapter = unitMetricAdapter
        bottomMiddleSpinner.adapter = unitMetricAdapter
        bottomRightSpinner.adapter = unitMetricAdapter
    }
}