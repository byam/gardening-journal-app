package com.example.gardeningjournalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.gardeningjournalapp.db.Plant
import com.example.gardeningjournalapp.db.PlantDao
import com.example.gardeningjournalapp.db.PlantDatabase

class PlantRepository(application: Application) {
    private val plantDao: PlantDao
    val plants: LiveData<List<Plant>>

    init {
        val db = PlantDatabase.getDatabase(application)
        plantDao = db.plantDao()
        plants = plantDao.getAll()
    }
    suspend fun insert(plant: Plant){
        plantDao.insert(plant)
    }
    suspend fun update(plant: Plant){
        plantDao.update(plant)
    }
    suspend fun delete(plant: Plant){
        plantDao.delete(plant)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return plantDao.getCount() == 0
    }
    fun getById(plantId: Int): LiveData<Plant> {
        return plantDao.getById(plantId)
    }
    suspend fun deleteById(plantId: Int){
        plantDao.deleteById(plantId)
    }
}
