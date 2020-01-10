package com.example.jeff.noteskotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query(
        "UPDATE  notes_table SET name = :name, " +
                "description = :description, completed = :completed , " +
                "dateEdited = :dateEdited WHERE id = :noteId "
    )
    suspend fun updateNoteById(
        noteId: Int,
        name: String?,
        description: String?,
        completed: Boolean,
        dateEdited: String?
    )


    @Query("DELETE FROM NOTES_TABLE WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    fun selectNoteById(noteId: Int): LiveData<Notes>

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun selectNotesDesc(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()


}