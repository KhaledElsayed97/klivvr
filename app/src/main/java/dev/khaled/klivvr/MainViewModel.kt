package dev.khaled.klivvr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.khaled.klivvr.R
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.model.CityGroup
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
    
    private val _cities = MutableStateFlow<List<CityGroup>>(emptyList())
    val cities: StateFlow<List<CityGroup>> = _cities.asStateFlow()
    
    init {
        viewModelScope.launch {
            _cities.value = getCitiesUseCase.invoke(R.raw.cities)
        }
    }
}