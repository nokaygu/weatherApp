package com.example.havadurumu.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havadurumu.config
import com.example.havadurumu.network.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HavaViewModel : ViewModel() {
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String> = _latitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String> = _longitude
    private val _days=MutableLiveData<List<Daily>>()
    val days: LiveData<List<Daily>> = _days

    init {

    }


     fun getHavaBilgisi() {
        viewModelScope.launch {
            try {
                val result = HavaApi.retrofitService
                    .getWeathers(
                        _latitude.value!!,
                        _longitude.value!!,
                        config.exclude,
                        config.appid,
                        config.lang,
                        config.units
                    )
                val obj = Json.decodeFromString<HavaBilgisi>(result)
                _days.value = obj.daily
            } catch (e: Exception) {

            }
        }
    }

}