package com.ranjan.malav.lifecycles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"

    override fun onCleared() {
        super.onCleared()
        logEvent("onCleared")
    }

    private val event: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>().also {
            it.value = null
        }
    }

    fun getEvent(): LiveData<String?> {
        return event
    }

    private fun logEvent(event: String) {
        val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT)
        val log = "LifecycleLogs $event invoked at ${sdf.format(Date())}"
        Log.d(TAG, log)
    }
}