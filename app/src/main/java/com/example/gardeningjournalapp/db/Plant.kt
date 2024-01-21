package com.example.gardeningjournalapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String,
    val wateringFrequency: Int,
    val plantingDate: String,
)
