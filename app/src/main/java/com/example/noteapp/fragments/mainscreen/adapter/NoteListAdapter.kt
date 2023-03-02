package com.example.noteapp.fragments.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.model.Note
import com.example.noteapp.databinding.NoteItemBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private var noteList = emptyList<Note>()

    inner class ViewHolder(
        val noteItem: NoteItemBinding
    ) : RecyclerView.ViewHolder(noteItem.root) {

        fun bindNote(note: Note) {
            noteItem.noteItemTitle.text = note.noteTitle
            noteItem.noteItemDetail.text = note.noteDescription
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.bindNote(currentItem)
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }
}