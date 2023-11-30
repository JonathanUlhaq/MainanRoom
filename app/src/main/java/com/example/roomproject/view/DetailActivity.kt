package com.example.roomproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roomproject.R
import com.example.roomproject.database.DatabaseNote
import com.example.roomproject.databinding.ActivityDetailBinding
import com.example.roomproject.entities.NoteEntity
import com.example.roomproject.repositories.NoteRepository
import com.example.roomproject.utils.ViewModelFactory
import com.example.roomproject.viewmodels.NoteViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID_NOTE")
        getData(noteViewModel(),getId!!.toInt())
        binding.btnAdd.setOnClickListener {
            addData(noteViewModel(),getId!!.toInt())
        }

    }

    fun noteViewModel(): NoteViewModel {
        val database = DatabaseNote(this)
        val repo = NoteRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    fun getData(viewModel: NoteViewModel,getId:Int) {

        viewModel.getNoteId(getId).observe(this) {
            binding.etName.setText(it.name)
            binding.etPhone.setText(it.number)
        }
    }

    fun addData(vm:NoteViewModel,id:Int) {
        vm.updateData(
            NoteEntity(
                id = id,
            name = binding.etName.text.toString(),
            number = binding.etPhone.text.toString()
        )
        ).let {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    fun  deleteData(vm:NoteViewModel,id:Int) {
//        vm.deleteData(
//            id
//        ).let {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
//        }
    }
}