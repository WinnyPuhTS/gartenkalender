package com.gartenkalender.app.ui.screens.plants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory
import com.gartenkalender.app.data.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<PlantCategory?>(null)
    val selectedCategory: StateFlow<PlantCategory?> = _selectedCategory

    val plants: StateFlow<List<Plant>> = _selectedCategory
        .flatMapLatest { cat ->
            if (cat == null) plantRepository.getAllPlants()
            else plantRepository.getPlantsByCategory(cat)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun selectCategory(category: PlantCategory?) {
        _selectedCategory.value = category
    }
}
