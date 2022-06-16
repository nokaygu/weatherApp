package com.example.havadurumu.network
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface HavaApiServis {
    @GET("onecall")
    suspend   fun getWeathers(
        @Query("lat") lat :String,
        @Query("lon") lon : String,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String,
        @Query("lang")  lang: String,
        @Query("units") units: String
    ): String
}

object HavaApi {
    val retrofitService : HavaApiServis by lazy {
        retrofit.create(HavaApiServis::class.java) }
}
