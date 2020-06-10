package com.example.remotehome.ui.livingroom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remotehome.network.SesorsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LivingRoomViewModel : ViewModel() {

    private val _mainLight = MutableLiveData<Boolean>()
    val mainLight: LiveData<Boolean>
        get() = _mainLight

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response


    init {
        getLivingRoomSensors()
    }

    private fun getLivingRoomSensors(){
        SesorsAPI.retrofitService.getProperties().enqueue(
            object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "ERROR"
                    Log.i("API", "ERROR: " + t.message)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                    Log.i("API", "" + _response.value )
                }
            })

    }
}