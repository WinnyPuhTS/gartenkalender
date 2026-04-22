package com.gartenkalender.app.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _selectedMonth = MutableStateFlow(LocalDate.now().monthValue)
    val selectedMonth: StateFlow<Int> = _selectedMonth

    val plantsForMonth: StateFlow<List<Plant>> = _selectedMonth
        .flatMapLatest { month -> plantRepository.getPlantsForMonth(month) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun selectMonth(month: Int) {
        _selectedMonth.value = month.coerceIn(1, 12)
    }
}
