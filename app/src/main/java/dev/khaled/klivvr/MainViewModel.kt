package dev.khaled.klivvr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    
    init {
        viewModelScope.launch {
            _cities.value = getCitiesUseCase.invoke()
        }
    }
}