package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.model.Note
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.fragments.mainscreen.MainPageFragmentDirections

class NoteListAdapter: ListAdapter<Note, NoteListAdapter.ViewHolder>(NoteDiffCallback()) {

    inner class ViewHolder(
        val binding: NoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(currentItem: Note) {
            binding.note = currentItem
            binding.root.setOnClickListener {
                val action = MainPageFragmentDirections.actionMainPageFragmentToUpdateNotesFragment(currentItem.id)
                Navigation.findNavController(it).navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position)!!)
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}