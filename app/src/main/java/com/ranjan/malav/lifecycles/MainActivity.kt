package com.ranjan.malav.lifecycles

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ranjan.malav.lifecycles.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "LifecycleLogs CREATED TASK ID: $taskId")
        logEvent("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getEvent().observe(this) {
            it?.let { logEvent(it) }
        }

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, ActivityA::class.java).also {
                it.flags = FLAG_ACTIVITY_SINGLE_TOP
            })
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java).also {
                it.flags = FLAG_ACTIVITY_NEW_TASK
            })
        }

        binding.btn3.setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java).also {
                it.flags = FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK
            })
        }

        binding.btn4.setOnClickListener {
            startActivity(Intent(this, ActivityStandard::class.java))
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
        Log.d(TAG, "LifecycleLogs DESTROYED TASK ID: $taskId")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logEvent("onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        logEvent("onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logEvent("onSaveInstanceState")
    }

    private fun logEvent(event: String) {
        val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT)
        val log = "LifecycleLogs $event invoked at ${sdf.format(Date())}"
        Log.d(TAG, log)

        if (::binding.isInitialized) {
            val newLogs = "${binding.tvLogs.text} \n$log"
            binding.tvLogs.text = newLogs
        }
    }

}