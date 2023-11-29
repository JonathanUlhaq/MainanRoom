package com.example.roomproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomproject.entities.NoteEntity
import com.example.roomproject.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    val repo: NoteRepository
) : ViewModel() {
    fun getAllNoteData() =
        repo.getAllNoteData()

    fun insertData(data: NoteEntity) =
        viewModelScope.launch {
            repo.insertData(data)
        }

    fun getNoteId(id:Int) =
        repo.getNoteId(id)

//    init {
//        insertData(data = NoteEntity(name = "Kulaih atau kerja", number = "08129392"))
//    }
}