package com.example.remotehome.ui.livingroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LivingRoomViewModel : ViewModel() {

    private val _mainLight = MutableLiveData<Boolean>()
    val mainLight: LiveData<Boolean>
        get() = _mainLight
}