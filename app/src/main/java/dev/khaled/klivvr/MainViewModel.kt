package dev.khaled.klivvr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.khaled.klivvr.data.structure.CityTrie
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.usecases.GetCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _cities = MutableStateFlow<List<CityDomainModel>>(emptyList())
    val cities: StateFlow<List<CityDomainModel>> = _cities.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var cityTrie: CityTrie? = null

    init {
        viewModelScope.launch {
            cityTrie = getCitiesUseCase.invoke(R.raw.cities)
            _cities.value = cityTrie?.getAllCities() ?: emptyList()
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query

        _cities.value = if (query.isBlank()) {
            cityTrie?.getAllCities() ?: emptyList()
        } else {
            cityTrie?.search(query) ?: emptyList()
        }
    }
}