package dev.khaled.klivvr.domain.usecases

import androidx.annotation.RawRes
import dev.khaled.klivvr.data.structure.CityTrie
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.repository.MainRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: MainRepository) {
    suspend operator fun invoke(@RawRes resId: Int): CityTrie {
        val sortedCities = repository.getCities(resId)
            .sortedWith(compareBy<CityDomainModel> { it.name }.thenBy { it.country })

        val trie = CityTrie()
        sortedCities.forEach { city ->
            trie.insert(city)
        }

        return trie
    }
}
