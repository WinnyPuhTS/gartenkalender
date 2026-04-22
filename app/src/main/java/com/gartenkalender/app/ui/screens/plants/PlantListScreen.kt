package com.gartenkalender.app.ui.screens.plants

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory

@Composable
fun PlantListScreen(viewModel: PlantListViewModel = hiltViewModel()) {
    val plants by viewModel.plants.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Pflanzendatenbank",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { viewModel.selectCategory(null) },
                label = { Text("Alle") },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            PlantCategory.values().forEach { cat ->
                FilterChip(
                    selected = selectedCategory == cat,
                    onClick = { viewModel.selectCategory(cat) },
                    label = { Text(cat.label()) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(plants) { plant ->
                PlantCard(plant)
            }
        }
    }
}

@Composable
fun PlantCard(plant: Plant) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(plant.name, style = MaterialTheme.typography.titleSmall)
            Text(plant.nameLatin, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            Text(plant.description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text("☀ ${plant.sunRequirement.label()}") })
                AssistChip(onClick = {}, label = { Text("💧 ${plant.wateringFrequency.label()}") })
            }
        }
    }
}

private fun PlantCategory.label(): String = when (this) {
    PlantCategory.VEGETABLE -> "Gemüse"
    PlantCategory.HERB -> "Kräuter"
    PlantCategory.FLOWER -> "Blumen"
}

private fun com.gartenkalender.app.data.model.SunRequirement.label(): String = when (this) {
    com.gartenkalender.app.data.model.SunRequirement.FULL_SUN -> "Vollsonne"
    com.gartenkalender.app.data.model.SunRequirement.PARTIAL_SHADE -> "Halbschatten"
    com.gartenkalender.app.data.model.SunRequirement.SHADE -> "Schatten"
}

private fun com.gartenkalender.app.data.model.WateringFrequency.label(): String = when (this) {
    com.gartenkalender.app.data.model.WateringFrequency.LOW -> "Wenig"
    com.gartenkalender.app.data.model.WateringFrequency.MEDIUM -> "Mittel"
    com.gartenkalender.app.data.model.WateringFrequency.HIGH -> "Viel"
}
