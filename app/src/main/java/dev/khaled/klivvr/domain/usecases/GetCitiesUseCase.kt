package dev.khaled.klivvr.domain.usecases

import androidx.annotation.RawRes
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.model.CityGroup
import dev.khaled.klivvr.domain.repository.MainRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: MainRepository) {
    suspend operator fun invoke(@RawRes resId: Int): List<CityGroup> {
        return groupCitiesByLetter(repository.getCities(resId).sortedWith(
            compareBy<CityDomainModel> { it.name }
                .thenBy { it.country })
        )
    }


    private fun groupCitiesByLetter(cities: List<CityDomainModel>): List<CityGroup> {
        return cities
            .groupBy { it.name.first().uppercaseChar() }
            .map { (letter, cityList) ->
                CityGroup(
                    letter = letter,
                    cities = cityList
                )
            }
            .sortedBy { it.letter }
    }
}
