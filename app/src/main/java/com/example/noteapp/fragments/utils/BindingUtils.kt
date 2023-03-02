package com.example.noteapp.fragments.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.noteapp.R
import com.example.noteapp.database.model.Note

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

@BindingAdapter("MarkColor")
fun ImageView.setCurserColor(item: Note?) {
    item?.let {
        setImageResource(when(item.noteColor){
            "blue" -> R.drawable.light_blue_mark
            "green" -> R.drawable.light_green_mark
            "pink" -> R.drawable.light_pink_mark
            "orange" -> R.drawable.light_orange_mark
            "purple" -> R.drawable.light_purple_mark
            else -> R.drawable.light_green_mark
        })
    }
}