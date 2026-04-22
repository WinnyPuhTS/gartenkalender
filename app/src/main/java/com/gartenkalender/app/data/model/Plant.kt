package com.gartenkalender.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a plant in the embedded database.
 * Category: VEGETABLE, HERB, FLOWER
 */
@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val nameLatin: String,
    val category: PlantCategory,
    val description: String,
    /** JSON array: months 1–12 when sowing indoors is recommended */
    val sowingIndoorMonths: String,
    /** JSON array: months 1–12 when sowing outdoors is recommended */
    val sowingOutdoorMonths: String,
    /** JSON array: months 1–12 when planting out is recommended */
    val plantingMonths: String,
    /** JSON array: months 1–12 when harvest is expected */
    val harvestMonths: String,
    val spacingCm: Int,
    val sunRequirement: SunRequirement,
    val wateringFrequency: WateringFrequency,
    val notes: String = ""
)

enum class PlantCategory { VEGETABLE, HERB, FLOWER }
enum class SunRequirement { FULL_SUN, PARTIAL_SHADE, SHADE }
enum class WateringFrequency { LOW, MEDIUM, HIGH }
