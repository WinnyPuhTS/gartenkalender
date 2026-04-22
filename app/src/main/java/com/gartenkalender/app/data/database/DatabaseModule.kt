package com.gartenkalender.app.data.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GartenkalenderDatabase {
        val db = GartenkalenderDatabase.create(context)
        CoroutineScope(Dispatchers.IO).launch {
            if (db.plantDao().getCount() == 0) {
                db.plantDao().insertAll(PlantSeedData.getPlants())
            }
        }
        return db
    }

    @Provides
    fun providePlantDao(db: GartenkalenderDatabase): PlantDao = db.plantDao()

    @Provides
    fun provideGardenTodoDao(db: GartenkalenderDatabase): GardenTodoDao = db.gardenTodoDao()
}
