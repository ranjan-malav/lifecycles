package com.ranjan.malav.lifecycles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ranjan.malav.lifecycles.databinding.ActivityLaunchModesBinding
import java.text.SimpleDateFormat
import java.util.*

class ActivitySingleTop : AppCompatActivity() {
    private val TAG = "ActivitySingleTop"

    private lateinit var binding: ActivityLaunchModesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "LifecycleLogs CREATED TASK ID: $taskId")
        logEvent("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchModesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTitle.text = TAG

        binding.btn1.text = "ActivitySingleInstance"
        binding.btn1.setOnClickListener {
            startActivity(Intent(this, ActivitySingleInstance::class.java))
        }

        binding.btn2.text = "ActivitySingleInstancePerTask"
        binding.btn2.setOnClickListener {
            startActivity(Intent(this, ActivitySingleInstancePerTask::class.java))
        }

        binding.btn3.text = "ActivitySingleTask"
        binding.btn3.setOnClickListener {
            startActivity(Intent(this, ActivitySingleTask::class.java))
        }

        binding.btn4.text = "ActivitySingleTop"
        binding.btn4.setOnClickListener {
            startActivity(Intent(this, ActivitySingleTop::class.java))
        }

        binding.btn5.text = "ActivityStandard"
        binding.btn5.setOnClickListener {
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