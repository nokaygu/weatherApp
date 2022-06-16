package com.example.havadurumu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.havadurumu.network.Daily
import com.example.havadurumu.network.HavaApi
import com.example.havadurumu.network.HavaBilgisi
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HavaViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
    private val _days=MutableLiveData<List<Daily>>()
    val days: LiveData<List<Daily>> = _days
    init {
        getHavaBilgisi()
    }
    private fun getHavaBilgisi() {
        viewModelScope.launch {
            try {
                val result = HavaApi.retrofitService
                    .getWeathers("41.00","28.97","current,minutely,hourly,alerts", "b1d5aca6b72b2d27342db242ad3d6b24", "tr", "metric")
                val obj= Json.decodeFromString<HavaBilgisi>(result)
                _days.value= obj.daily
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }   
        }
    }
}