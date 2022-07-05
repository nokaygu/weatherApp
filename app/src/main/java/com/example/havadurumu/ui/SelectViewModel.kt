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


class SelectViewModel : ViewModel() {
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String> = _latitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String> = _longitude

    init {


    }

      fun cityCall(cityname:String) {

viewModelScope.launch {runBlocking{
    try {
                val retrofitService : CityApiServis = retrofit2.create(CityApiServis::class.java)
                val obj = retrofitService
                    .getCity(cityname, "1", "b1d5aca6b72b2d27342db242ad3d6b24")
                _latitude.value = obj[0].lat.toString()
                _longitude.value = obj[0].lon.toFloat().toString()

            } catch (e: Exception) {
                Log.d("error", "error", e)

        }
    }}
}
}