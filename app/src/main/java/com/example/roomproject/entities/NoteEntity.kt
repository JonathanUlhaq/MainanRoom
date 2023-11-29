package com.example.roomproject.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,
    @ColumnInfo(name = "title")
    val name:String = "",
    @ColumnInfo(name = "desc")
    val number:String = ""
)
