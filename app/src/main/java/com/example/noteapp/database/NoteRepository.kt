package com.example.noteapp.database

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val readALlData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }
}