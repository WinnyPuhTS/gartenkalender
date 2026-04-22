package com.gartenkalender.app.data.repository

import com.gartenkalender.app.data.database.PlantDao
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(
    private val plantDao: PlantDao
) {
    fun getAllPlants(): Flow<List<Plant>> = plantDao.getAllPlants()

    fun getPlantsByCategory(category: PlantCategory): Flow<List<Plant>> =
        plantDao.getPlantsByCategory(category)

    fun getPlantsForMonth(month: Int): Flow<List<Plant>> =
        plantDao.getPlantsForMonth(month)

    suspend fun getPlantById(id: Long): Plant? = plantDao.getPlantById(id)

    suspend fun insert(plant: Plant) = plantDao.insert(plant)

    suspend fun insertAll(plants: List<Plant>) = plantDao.insertAll(plants)

    suspend fun getCount(): Int = plantDao.getCount()
}
