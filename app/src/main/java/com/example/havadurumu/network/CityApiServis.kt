package com.example.havadurumu.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL2 =
    "https://api.openweathermap.org/geo/1.0/"
val contentType = "application/json".toMediaType()
val json =Json { ignoreUnknownKeys = true }
val retrofit2 = Retrofit.Builder()
    .baseUrl(BASE_URL2)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()

interface CityApiServis {
    @GET("direct")
   suspend fun getCity(
        @Query("q") q :String,
        @Query("limit") limit : String,
        @Query("appid") appid: String
    ):List<CityItem>
}
/*object CityApi {
    val retrofitService : CityApiServis =retrofit2.create(CityApiServis::class.java)
}*/