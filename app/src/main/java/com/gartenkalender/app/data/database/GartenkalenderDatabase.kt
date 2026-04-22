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
            return Room.databaseBuilder(
                context,
                GartenkalenderDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }
}
