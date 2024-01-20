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

//    fun getById(plantId: Int) = viewModelScope.launch {
//        return repository.getById(plantId)
//    }
}
