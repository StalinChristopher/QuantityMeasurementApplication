package com.yml.quantitymeasurementapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG : String = "status"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG,"Process created")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"Process started")
        println("Process is started")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"Process restarted")
        println("Process is restarted")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"Process paused")
        println("Process is paused")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"Process resumed")
        println("Process is resumed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"Process destroyed")
        println("Process is destroyed")
    }
}