package com.example.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteDescription: String,
    val noteColor: String
        )