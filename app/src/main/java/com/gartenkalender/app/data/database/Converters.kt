package com.gartenkalender.app.data.database

import androidx.room.TypeConverter
import com.gartenkalender.app.data.model.PlantCategory
import com.gartenkalender.app.data.model.SunRequirement
import com.gartenkalender.app.data.model.WateringFrequency
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.toString()

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun fromPlantCategory(c: PlantCategory): String = c.name

    @TypeConverter
    fun toPlantCategory(v: String): PlantCategory = PlantCategory.valueOf(v)

    @TypeConverter
    fun fromSunRequirement(s: SunRequirement): String = s.name

    @TypeConverter
    fun toSunRequirement(v: String): SunRequirement = SunRequirement.valueOf(v)

    @TypeConverter
    fun fromWateringFrequency(w: WateringFrequency): String = w.name

    @TypeConverter
    fun toWateringFrequency(v: String): WateringFrequency = WateringFrequency.valueOf(v)
}
