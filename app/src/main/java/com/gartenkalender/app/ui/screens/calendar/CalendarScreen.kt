package com.gartenkalender.app.ui.screens.calendar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gartenkalender.app.data.model.Plant
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = hiltViewModel()) {
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val plants by viewModel.plantsForMonth.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Gartenkalender",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Month selector
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp)
        ) {
            (1..12).forEach { month ->
                val label = Month.of(month).getDisplayName(TextStyle.SHORT, Locale.GERMAN)
                FilterChip(
                    selected = month == selectedMonth,
                    onClick = { viewModel.selectMonth(month) },
                    label = { Text(label) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val monthName = Month.of(selectedMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.GERMAN)
        Text(
            text = "Empfehlungen für $monthName",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (plants.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Keine Empfehlungen für diesen Monat.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(plants) { plant ->
                    PlantCalendarCard(plant = plant, month = selectedMonth)
                }
            }
        }
    }
}

@Composable
fun PlantCalendarCard(plant: Plant, month: Int) {
    val actions = buildActions(plant, month)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = plant.name, style = MaterialTheme.typography.titleSmall)
            Text(text = plant.nameLatin, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            actions.forEach { action ->
                Text("• $action", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

private fun buildActions(plant: Plant, month: Int): List<String> {
    val actions = mutableListOf<String>()
    val m = month.toString()
    if (plant.sowingIndoorMonths.split(",").contains(m)) actions.add("Vorziehen (drinnen)")
    if (plant.sowingOutdoorMonths.split(",").contains(m)) actions.add("Aussäen (draußen)")
    if (plant.plantingMonths.split(",").contains(m)) actions.add("Auspflanzen")
    if (plant.harvestMonths.split(",").contains(m)) actions.add("Ernte möglich")
    return actions
}
