package com.ranjan.malav.lifecycles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ranjan.malav.lifecycles.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class ActivityA : AppCompatActivity() {
    private val TAG = "ActivityA"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        logEvent("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = "ActivityA"

        binding.btn1.text = "ActivityA"
        binding.btn1.setOnClickListener {
            startActivity(Intent(this, ActivityA::class.java))
        }

        binding.btn2.text = "ActivityB"
        binding.btn2.setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }

        binding.btn3.text = "ActivityC"
        binding.btn3.setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        logEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logEvent("onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logEvent("onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        logEvent("onRestart")
    }

    private fun logEvent(event: String) {
        val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT)
        val log = "$event invoked at ${sdf.format(Date())}"
        Log.d(TAG, log)

        if (::binding.isInitialized) {
            val newLogs = "${binding.tvLogs.text} \n$log"
            binding.tvLogs.text = newLogs
        }
    }

}