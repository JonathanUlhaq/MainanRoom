package com.example.roomproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.databinding.ItemLayoutBinding
import com.example.roomproject.entities.NoteEntity

class MainRvAdapter(private val list: List<NoteEntity>,val onDelete:(NoteEntity) -> Unit,val onClick:(Int) -> Unit) :
    RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvName.text = this.name
                binding.tvNumber.text = this.number
                binding.cdCard.setOnClickListener {
                    onClick.invoke(this.id)
                }
                binding.ivTrash.setOnClickListener {
                    onDelete.invoke(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}