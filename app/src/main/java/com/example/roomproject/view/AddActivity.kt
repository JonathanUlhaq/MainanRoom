package com.example.roomproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roomproject.R
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.databinding.ActivityAddBinding
import com.example.roomproject.entities.NoteEntity
import com.example.roomproject.repositories.NoteRepository
import com.example.roomproject.utils.ViewModelFactory
import com.example.roomproject.viewmodels.NoteViewModel

class AddActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addData(noteViewModel())
        }
    }

    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun addData(vm:NoteViewModel) {
        vm.insertData(NoteEntity(
            name = binding.etName.text.toString(),
            number = binding.etPhone.text.toString()
        )).let {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}