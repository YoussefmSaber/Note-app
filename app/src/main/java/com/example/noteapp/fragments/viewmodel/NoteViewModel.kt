package com.example.noteapp.fragments.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.noteapp.database.model.Note
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _readAllData: LiveData<List<Note>>
    val readAllData get() = _readAllData

    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()

        repository = NoteRepository(noteDao)
        _readAllData = repository.readALlData
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun getNoteById(id: Int): LiveData<Note> = liveData(Dispatchers.IO) {
        val note = repository.getNoteById(id)
        emit(note)
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Note>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}