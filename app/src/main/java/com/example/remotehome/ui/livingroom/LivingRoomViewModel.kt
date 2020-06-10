package com.example.remotehome.ui.livingroom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remotehome.network.SensorAPI
import com.example.remotehome.network.SensorProperty
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
        SensorAPI.retrofitService.getProperties().enqueue(
            object:Callback<List<SensorProperty>>  {
                override fun onFailure(call: Call<List<SensorProperty>>, t: Throwable) {
                    _response.value = "ERROR"
                    Log.i("API", "ERROR: " + t.message)
                }

                override fun onResponse(call: Call<List<SensorProperty>>, response: Response<List<SensorProperty>>) {
                    _response.value =    "Success: ${response.body()?.size} Mars properties retrieved"
                    Log.i("API", "SIZE : " + response.body()?.size )
                }
            })

    }
}