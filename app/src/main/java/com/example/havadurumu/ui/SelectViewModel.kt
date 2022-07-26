package com.example.havadurumu.ui
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.havadurumu.config
import com.example.havadurumu.network.CityApiServis
import com.example.havadurumu.network.CityItem
import com.example.havadurumu.network.retrofit2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectViewModel : ViewModel() {
    private val _latitude = MutableLiveData<String>()
    val latitude: MutableLiveData<String> = _latitude
    private val _longitude = MutableLiveData<String>()
    val longitude: MutableLiveData<String> = _longitude

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent
    init {


    }

    fun cityCall(cityname:String) {

        val retrofitService2 : CityApiServis = retrofit2.create(CityApiServis::class.java)
        val call: Call<List<CityItem>> = retrofitService2.getCity(cityname, config.limit, config.appid)
        call.enqueue(object : Callback<List<CityItem>> {
            override fun onResponse(
                call: Call<List<CityItem>>,
                response: Response<List<CityItem>>
            ) {
                response.body()?.let{
                _latitude.value = it.get(0).lat.toString()
                _longitude.value = it.get(0).lon.toString()
                _openTaskEvent.value=Event("tamam")}

            }

            override fun onFailure(call: Call<List<CityItem>>, t: Throwable) {
                _openTaskEvent.value=Event("problem")
            }
        })
    }
}