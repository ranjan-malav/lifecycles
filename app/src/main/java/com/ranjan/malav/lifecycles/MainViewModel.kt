package com.ranjan.malav.lifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val string: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            it.value = "Hello World"
        }
    }

    fun getString(): LiveData<String> {
        return string
    }
}