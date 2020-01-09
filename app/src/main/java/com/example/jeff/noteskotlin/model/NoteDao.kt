package com.example.jeff.noteskotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    suspend fun selectNoteById(noteId: Int): LiveData<Notes>

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    suspend fun selectNotesAsc(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()


}