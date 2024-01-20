package com.example.gardeningjournalapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAll(): LiveData<List<Plant>>
    @Query("SELECT * FROM plants WHERE id = :plantId")
    suspend fun getById(plantId: Int): LiveData<Plant>
    @Insert
    suspend fun insert(plant: Plant)
    @Update
    suspend fun update(plant: Plant)
    @Delete
    suspend fun delete(plant: Plant)
    @Query("DELETE FROM plants")
    suspend fun deleteAll()
    @Query("DELETE FROM plants WHERE id = :plantId")
    suspend fun deleteById(plantId: Int)
}
