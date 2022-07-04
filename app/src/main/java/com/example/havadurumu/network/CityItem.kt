package com.example.havadurumu.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityItem(
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("local_names")
    val localNames: LocalNames,
    @SerialName("lon")
    val lon: Double,
    @SerialName("name")
    val name: String
)