package com.example.gardeningjournalapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plant::class], version = 1)
abstract class PlantDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null
        private val LOCK = Any() // The root of the Kotlin class hierarchy. Every Kotlin class has [Any] as a superclass.

        // Invoke check if the instance is not null return the instance, if it is null
        // synchronized block work, inside this also check null or not and call the function buildDatabase
        operator fun invoke(context: Context) = PlantDatabase.INSTANCE
            ?: synchronized(PlantDatabase.LOCK){
                PlantDatabase.INSTANCE ?: PlantDatabase.getDatabase(context).also {
                PlantDatabase.INSTANCE = it
            }
        }
        fun getDatabase(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = PlantDatabase::class.java,
                    name = "plant_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
