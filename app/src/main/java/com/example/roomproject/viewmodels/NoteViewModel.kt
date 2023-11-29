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

//    init {
//        insertData(data = NoteEntity(name = "Halo dek", number = "08129392"))
//    }
}