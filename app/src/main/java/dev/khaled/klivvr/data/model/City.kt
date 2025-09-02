package dev.khaled.klivvr.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val lon: Double,
    val lat: Double
)

@Serializable
data class City(
    val country: String,
    val name: String,
    @SerialName("_id") val id: Long,
    val coord: Coordinates
)
