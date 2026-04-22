package com.gartenkalender.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name ASC")
    fun getAllPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE category = :category ORDER BY name ASC")
    fun getPlantsByCategory(category: PlantCategory): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :id")
    suspend fun getPlantById(id: Long): Plant?

    @Query("""
        SELECT * FROM plants
        WHERE sowingIndoorMonths LIKE '%' || :month || '%'
           OR sowingOutdoorMonths LIKE '%' || :month || '%'
           OR plantingMonths LIKE '%' || :month || '%'
           OR harvestMonths LIKE '%' || :month || '%'
        ORDER BY name ASC
    """)
    fun getPlantsForMonth(month: Int): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Plant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Plant)

    @Update
    suspend fun update(plant: Plant)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("SELECT COUNT(*) FROM plants")
    suspend fun getCount(): Int
}
