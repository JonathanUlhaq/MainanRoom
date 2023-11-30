package com.example.roomproject.repositories

import androidx.lifecycle.LiveData
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.entities.NoteEntity

class NoteRepository(
    val database:DatabaseNote
) {
    fun getAllNoteData():LiveData<List<NoteEntity>> = database.dao().getAllNoteData()
    fun getNoteId(id:Int):LiveData<NoteEntity> = database.dao().getNoteId(id)
    suspend fun insertData(data:NoteEntity) = database.dao().insertData(data)
    suspend fun updateData(data:NoteEntity) = database.dao().updateData(data)
    suspend fun deleteData(id:NoteEntity) = database.dao().deleteData(id)
}