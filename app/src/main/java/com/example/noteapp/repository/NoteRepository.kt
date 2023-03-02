package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.database.NoteDao
import com.example.noteapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readALlData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }
}