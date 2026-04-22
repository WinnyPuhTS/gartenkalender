package com.gartenkalender.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gartenkalender.app.data.model.GardenTodo
import com.gartenkalender.app.data.model.Plant

@Database(
    entities = [Plant::class, GardenTodo::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class GartenkalenderDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun gardenTodoDao(): GardenTodoDao

    companion object {
        const val DATABASE_NAME = "gartenkalender.db"

        fun create(context: Context): GartenkalenderDatabase {
            var instance: GartenkalenderDatabase? = null
            val db = Room.databaseBuilder(
                context,
                GartenkalenderDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed embedded plant data on first creation
                        CoroutineScope(Dispatchers.IO).launch {
                            instance?.plantDao()?.let { dao ->
                                if (dao.getCount() == 0) {
                                    dao.insertAll(PlantSeedData.getPlants())
                                }
                            }
                        }
                    }
                })
                .build()
            instance = db
            return db
        }
    }
}
