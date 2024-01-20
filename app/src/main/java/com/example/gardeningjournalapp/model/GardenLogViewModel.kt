package com.example.gardeningjournalapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gardeningjournalapp.db.Plant
import com.example.gardeningjournalapp.repository.PlantRepository
import kotlinx.coroutines.launch

class GardenLogViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PlantRepository
    val plants: LiveData<List<Plant>>

    init {
        repository = PlantRepository(application)
        plants = repository.plants
    }

    fun insert(plant: Plant) = viewModelScope.launch {
        repository.insert(plant)
    }

    fun update(plant: Plant) = viewModelScope.launch {
        repository.update(plant)
    }

    fun delete(plant: Plant) = viewModelScope.launch {
        repository.delete(plant)
    }

    fun initializeSampleDataIfEmpty() = viewModelScope.launch {
        if (repository.isDatabaseEmpty()) {
            val samplePlants = listOf(
                Plant(name = "Rose", type = "Flower", wateringFrequency = 2, plantingDate = "2023-01-01"),
                Plant(name = "Tomato", type = "Vegetable", wateringFrequency = 3, plantingDate = "2023-02-15"),
                Plant(name = "Basil", type = "Herb", wateringFrequency = 1, plantingDate = "2023-03-10")
            )
            for (plant in samplePlants) {
                insert(plant)
            }
        }
    }

//    fun getById(plantId: Int) = viewModelScope.launch {
//        return repository.getById(plantId)
//    }
}
