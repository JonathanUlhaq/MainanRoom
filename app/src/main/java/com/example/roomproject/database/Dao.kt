package com.example.roomproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomproject.entities.NoteEntity

@Dao
interface Dao {
    @Query("SELECT * FROM tb_note")
    fun getAllNoteData():LiveData<List<NoteEntity>>

    @Query("SELECT * FROM tb_note WHERE id = :id")
    fun getNoteId(id:Int):LiveData<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:NoteEntity)

    @Update()
    suspend fun updateData(data: NoteEntity)

    @Delete()
    suspend fun deleteData(id: NoteEntity)
}