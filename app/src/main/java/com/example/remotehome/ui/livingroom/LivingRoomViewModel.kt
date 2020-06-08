package com.example.remotehome.ui.livingroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LivingRoomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Controles del salón"
    }
    val text: LiveData<String> = _text
}