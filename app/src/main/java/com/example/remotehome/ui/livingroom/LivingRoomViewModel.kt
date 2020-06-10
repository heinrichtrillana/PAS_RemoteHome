package com.example.remotehome.ui.livingroom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remotehome.network.SensorAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LivingRoomViewModel : ViewModel() {

    private val _mainLight = MutableLiveData<Boolean>()
    val mainLight: LiveData<Boolean>
        get() = _mainLight

    private val _temperature = MutableLiveData<Double>()
    val temperature: LiveData<Double>
        get() = _temperature

    private val _humidity = MutableLiveData<Double>()
    val humidity: LiveData<Double>
        get() = _humidity

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    init {
        getLivingRoomSensors()
    }



    private fun getLivingRoomSensors(){

        coroutineScope.launch {
            var getSensorValuesDeferred = SensorAPI.retrofitService.getSensorValues("Madrid,ES")
            try {
                var result = getSensorValuesDeferred.await()

                _temperature.value = result.data[0].temperature
                _humidity.value = result.data[0].relativeHumidity

                Log.i("API", result.data[0].temperature.toString())
                Log.i("API", result.data[0].relativeHumidity.toString())


            } catch (e: Exception) {
                _temperature.value = 0.0
                _humidity.value = 0.0
                Log.i("API", "ERROR: " + e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}