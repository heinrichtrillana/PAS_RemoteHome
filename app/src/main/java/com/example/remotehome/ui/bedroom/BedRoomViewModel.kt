package com.example.remotehome.ui.bedroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BedRoomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Controles de la habitación"
    }
    val text: LiveData<String> = _text
}