package dev.khaled.klivvr.domain.usecases

import dev.khaled.klivvr.R
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.repository.MainRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: MainRepository) {
    suspend operator fun invoke(): List<CityDomainModel> {
        return repository.getCities(R.raw.cities)
    }
}
