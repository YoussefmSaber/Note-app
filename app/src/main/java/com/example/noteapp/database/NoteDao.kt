package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): Note
}