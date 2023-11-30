package com.example.roomproject.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    @SuppressLint("NotifyDataSetChanged")
    fun init(vm:NoteViewModel) {
        changeData(vm) {
            list ->

            val layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvList.layoutManager = layoutManager
            adapter = MainRvAdapter(list, onDelete = { item ->
                vm.deleteData(item).let {
                    Toast.makeText(this,"Udah kehapus",Toast.LENGTH_SHORT).show()
                }
            }) {
                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra("ID_NOTE",it.toString())
                startActivity(intent)
            }
            adapter.notifyDataSetChanged()
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
// HILT DAGGER
//  1. Import Dependency
//    2. Membuat Entity ( Table )
//    3. Dao ( Data access Object )
//    4. Database untuk menyimpan data
//    5. Repository
//    6. ViewModel
//    7. ViewModel Factory
//    8. View


    fun changeData(vm: NoteViewModel, getData: (List<NoteEntity>) -> Unit) {
        vm.getAllNoteData().observe(this@MainActivity) { list ->
            if (list.isNotEmpty()) {
                getData.invoke(list)
                binding.rvList.visibility = View.VISIBLE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.rvList.visibility = View.GONE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }


//    fun changeData(vm: NoteViewModel,getData:(List<NoteEntity>) -> Unit) {
//        vm.getAllNoteData().observe(this@MainActivity) { list ->
//            if (!list.isNullOrEmpty()) {
//                getData.invoke(list)
//                binding.flList.visibility = View.VISIBLE
//                binding.tvEmpty.visibility = View.GONE
//            } else {
//                binding.flList.visibility = View.GONE
//                binding.tvEmpty.visibility = View.VISIBLE
//            }
//        }
//    }
}