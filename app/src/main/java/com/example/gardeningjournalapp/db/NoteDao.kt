package com.example.gardeningjournalapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note:Note)
    @Query("SELECT * FROM NOTE ORDER BY id DESC")
    suspend fun getAllNotes():List<Note>
    @Insert
    suspend fun addMultipleNotes(vararg note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
}
