package com.example.remotehome.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = " https://api.weatherbit.io/v2.0/"
private const val API_KEY = "4b21bfdf867d419e8666dd7f4dff7b92"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface SensorApiService {
    @GET("current?key=$API_KEY")
    fun getSensorValues(@Query("city") city : String):
            Deferred<SensorData>
}
object SensorAPI {
    val retrofitService : SensorApiService by lazy {
        retrofit.create(SensorApiService::class.java) }
}
