package com.example.havadurumu.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.havadurumu.network.CityApiServis
import com.example.havadurumu.network.CityItem
import com.example.havadurumu.network.retrofit2
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.internal.notifyAll
import okhttp3.internal.wait

class SelectViewModel : ViewModel() {
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String> = _latitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String> = _longitude
    private val _cityName = MutableLiveData<String>()
    val cityName: MutableLiveData<String> = _cityName

    init {
        Log.d("kaykil", "noluyor")

    }

      fun cityCall(cityname:String) {

viewModelScope.launch {runBlocking{
    try {
                val retrofitService : CityApiServis = retrofit2.create(CityApiServis::class.java)
                Log.d("kaykil", "trydayım")
                val obj = retrofitService
                    .getCity(cityname, "1", "b1d5aca6b72b2d27342db242ad3d6b24")
               // val format = Json { ignoreUnknownKeys = true }
                //val obj = format.decodeFromString<List<CityItem>>(result2)
                Log.d("kaykil", "latututtaym")
                _latitude.value = obj[0].lat.toString()
                _longitude.value = obj[0].lon.toFloat().toString()
                Log.d("kaykil", "boluyor")
            } catch (e: Exception) {
                Log.d("kaykil", "kaykildim", e)

        }
    }}
}
}