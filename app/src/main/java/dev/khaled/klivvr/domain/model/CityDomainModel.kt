package dev.khaled.klivvr.domain.model

data class CityDomainModel(
    val id: Long,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
