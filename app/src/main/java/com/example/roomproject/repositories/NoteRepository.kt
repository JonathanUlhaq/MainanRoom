package com.example.roomproject.repositories

import androidx.lifecycle.LiveData
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.entities.NoteEntity

class NoteRepository(
    val database:DatabaseNote
) {
    fun getAllNoteData():LiveData<List<NoteEntity>> = database.dao().getAllNoteData()
    suspend fun insertData(data:NoteEntity) = database.dao().insertData(data)
}