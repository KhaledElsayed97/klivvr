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

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var cityTrie: CityTrie? = null
    private var citiesList: List<CityDomainModel> = emptyList()

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            try {
                cityTrie = getCitiesUseCase.invoke(R.raw.cities)
                citiesList = cityTrie?.getAllCities() ?: emptyList()
                _cities.value = citiesList
            } catch (e: Exception) {
                _cities.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query

        _cities.value = if (query.isBlank()) {
            citiesList
        } else {
            cityTrie?.search(query) ?: emptyList()
        }
    }
}