package com.example.havadurumu.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HavaBilgisi(
    @SerialName("daily")
    val daily: List<Daily>,
    @SerialName("lat")
    val lat: Int,
    @SerialName("lon")
    val lon: Double,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: Int
)