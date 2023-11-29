package com.example.roomproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roomproject.R
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.databinding.ActivityDetailBinding
import com.example.roomproject.repositories.NoteRepository
import com.example.roomproject.utils.ViewModelFactory
import com.example.roomproject.viewmodels.NoteViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData(noteViewModel())
    }

    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun getData(viewModel: NoteViewModel) {
        val getId = intent.getStringExtra("ID_NOTE")
        viewModel.getNoteId(getId!!.toInt()).observe(this) {
            binding.tvName.text = it.name
            binding.tvPhone.text = it.number
        }
    }
}