package com.yml.quantitymeasurementapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yml.quantitymeasurementapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    companion object{
        val TAG : String = "status"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG,"Process created")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertQuantityButton.setOnClickListener {
            replaceFragment(ConvertQuantityFragment())
        }

        binding.addQuantityButton.setOnClickListener {
            replaceFragment(AddQuantityFragment())
        }


    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
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