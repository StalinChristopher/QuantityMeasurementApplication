package com.yml.quantitymeasurementapplication.util

import android.util.Log
import android.view.View
import android.widget.EditText

object ConvertValues {
    fun calculateLength(inputValue : Double, leftUnit : String, rightUnit : String) : Double{
        Log.i("units","left $leftUnit")
        Log.i("units","right $rightUnit")
        return when(leftUnit){
            "CENTIMETER" -> {
                convertCentimeter(inputValue,rightUnit)
            }
            "METER" -> {
                convertMeter(inputValue,rightUnit)
            }
            "KILOMETER" -> {
                convertKilometer(inputValue, rightUnit)
            }
            else -> 0.0
        }
    }

    fun calculateWeight(inputValue : Double, leftUnit : String, rightUnit : String) : Double {
        return when(leftUnit){
            "GRAM" -> {
                convertGrams(inputValue,rightUnit)
            }
            "KILOGRAM" -> {
                convertKilograms(inputValue, rightUnit)
            }
            "TONNE" -> {
                convertTonne(inputValue, rightUnit)
            }
            else -> 0.0
        }
    }

    fun calculateTemperature(inputValue : Double, leftUnit : String, rightUnit : String) : Double {
        return when(leftUnit){
            "CELSIUS" -> {
                convertCelsius(inputValue, rightUnit)
            }
            "FAHRENHEIT" -> {
                convertFahrenheit(inputValue, rightUnit)
            }
            "KELVIN" -> {
                convertKelvin(inputValue, rightUnit)
            }
            else -> 0.0
        }
    }

    fun calculateVolume(inputValue : Double, leftUnit : String, rightUnit : String) : Double{
        return when(leftUnit){
            "MILLILITRE" -> {
                convertMillilitre(inputValue, rightUnit)
            }
            "LITRE" -> {
                convertLitre(inputValue, rightUnit)
            }
            "GALLON" -> {
                convertGallon(inputValue, rightUnit)
            }
            else -> 0.0
        }
    }

    private fun convertGallon(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "MILLILITRE" -> { inputValue * 3785.41}
            "LITRE" -> {inputValue * 3.78541}
            "GALLON" -> { inputValue}
            else -> 0.0
        }
    }

    private fun convertLitre(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "MILLILITRE" -> { inputValue * 1000}
            "LITRE" -> {inputValue}
            "GALLON" -> { inputValue / 3.785}
            else -> 0.0
        }
    }

    private fun convertMillilitre(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "MILLILITRE" -> { inputValue}
            "LITRE" -> {inputValue/1000}
            "GALLON" -> { inputValue / 3785}
            else -> 0.0
        }
    }

    private fun convertKelvin(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CELSIUS" -> {
                inputValue - 273.15
            }
            "FAHRENHEIT" -> {
                return (inputValue - 273.15) * (9/5) +32
            }
            "KELVIN" -> {
                return inputValue
            }
            else -> 0.0
        }
    }

    private fun convertFahrenheit(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CELSIUS" -> {
                (inputValue - 32 ) * (5.0/9.0)
            }
            "FAHRENHEIT" -> {
                inputValue
            }
            "KELVIN" -> {
               (inputValue - 32 ) * (5/9) + 273.15
            }
            else -> 0.0
        }
    }

    private fun convertCelsius(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CELSIUS" -> {
                inputValue
            }
            "FAHRENHEIT" -> {
                inputValue * (9/5) +32
            }
            "KELVIN" -> {
                return inputValue + 273.15
            }
            else -> 0.0
        }
    }

    private fun convertKilometer(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CENTIMETER" -> {
                return inputValue * 100000
            }
            "METER" -> {
                return inputValue * 1000
            }
            "KILOMETER" -> {
                return inputValue
            }
            else -> 0.0
        }

    }

    private fun convertMeter(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CENTIMETER" -> {
                return inputValue * 100
            }
            "METER" -> {
                return inputValue

            }
            "KILOMETER" -> {
                return inputValue / 1000
            }
            else -> 0.0
        }

    }

    private fun convertCentimeter(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "CENTIMETER" -> {
                return inputValue
            }
            "METER" -> {
                return inputValue / 100
            }
            "KILOMETER" -> {
                return inputValue / 100000
            }
            else -> 0.0
        }

    }
    
    private fun convertTonne(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "GRAM" -> {
                return inputValue * 1000000
            }
            "KILOGRAM" -> {
                return inputValue * 1000
            }
            "TONNE" -> {
                return inputValue
            }
            else -> 0.0
        }
    }

    private fun convertKilograms(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "GRAM" -> {
                return inputValue * 1000
            }
            "KILOGRAM" -> {
                return inputValue
            }
            "TONNE" -> {
                return inputValue / 1000
            }
            else -> 0.0
        }
    }

    private fun convertGrams(inputValue: Double, rightUnit: String): Double {
        return when(rightUnit){
            "GRAM" -> {
                return inputValue
            }
            "KILOGRAM" -> {
                return inputValue / 1000
            }
            "TONNE" -> {
                return inputValue * 0.000001
            }
            else -> 0.0
        }

    }
    private fun convertTo(selectedMetric: String, firstUnit: String, resultUnit: String, inputValue: Double) : Double {
        if(inputValue == Double.MAX_VALUE){
            return 0.0
        }
        return when(selectedMetric){
            "Length" -> calculateLength(inputValue,firstUnit,resultUnit)
            "Weight" -> calculateWeight(inputValue,firstUnit,resultUnit)
            "Volume" -> calculateVolume(inputValue,firstUnit,resultUnit)
            "Temperature" -> calculateTemperature(inputValue,firstUnit,resultUnit)
            else -> 0.0
        }
    }

    fun addMetricValues(selectedMetric: String, firstUnit: String, secondUnit: String, resultUnit: String, input1: Double, input2: Double): Double {
        var firstValue = convertTo(selectedMetric,firstUnit, resultUnit,input1)
        var secondValue = convertTo(selectedMetric,secondUnit,resultUnit,input2)
        return firstValue + secondValue
    }


}