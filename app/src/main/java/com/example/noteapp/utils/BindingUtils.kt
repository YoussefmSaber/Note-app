package com.example.noteapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.noteapp.model.Note

@BindingAdapter("NoteTitle")
fun TextView.setNoteTitle(item: Note?) {
    item?.let {
        text = item.noteTitle
    }
}

@BindingAdapter("NoteDetail")
fun TextView.setNoteDetail(item: Note?) {
    item?.let {
        text = item.noteDescription
    }
}