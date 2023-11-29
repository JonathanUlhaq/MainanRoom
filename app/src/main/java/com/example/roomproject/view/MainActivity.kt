package com.example.roomproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomproject.adapters.MainRvAdapter
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.entities.NoteEntity
import com.example.roomproject.repositories.NoteRepository
import com.example.roomproject.utils.ViewModelFactory
import com.example.roomproject.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:MainRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(noteViewModel())

    }



    fun init(vm:NoteViewModel) {
        changeData(vm) {
            list ->
            val layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvList.layoutManager = layoutManager
            adapter = MainRvAdapter(list) {
                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra("ID_NOTE",it.toString())
                startActivity(intent)
            }
            binding.rvList.adapter = adapter
        }
        onClick()

    }
    fun onClick() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
    }
    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun changeData(vm: NoteViewModel,getData:(List<NoteEntity>) -> Unit) {
        vm.getAllNoteData().observe(this@MainActivity) { list ->
            if (!list.isNullOrEmpty()) {
                getData.invoke(list)
            }
        }
    }
}