package com.gartenkalender.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gartenkalender.app.data.model.GardenTodo
import com.gartenkalender.app.data.model.Plant
import com.gartenkalender.app.data.model.PlantCategory
import com.gartenkalender.app.data.model.SunRequirement
import com.gartenkalender.app.data.model.WateringFrequency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            return Room.databaseBuilder(
                context,
                GartenkalenderDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed embedded plant data on first creation
                        CoroutineScope(Dispatchers.IO).launch {
                            // Seeding happens via DatabaseModule after build
                        }
                    }
                })
                .build()
        }
    }
}
