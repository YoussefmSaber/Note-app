package com.example.noteapp.database.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.database.NoteDao
import com.example.noteapp.database.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val readALlData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    fun getNoteById(id: Int): Note {
        return noteDao.getNoteById(id)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Note>> {
        return noteDao.searchDatabase(searchQuery)
    }
}