package com.example.havadurumu.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalNames(
    @SerialName("ar")
    val ar: String,
    @SerialName("el")
    val el: String,
    @SerialName("ru")
    val ru: String,
    @SerialName("tr")
    val tr: String

)