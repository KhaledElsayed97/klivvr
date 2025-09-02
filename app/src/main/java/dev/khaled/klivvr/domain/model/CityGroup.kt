package dev.khaled.klivvr.domain.model

data class CityGroup(
    val letter: Char,
    val cities: List<CityDomainModel>
)
