package com.example.jeff.noteskotlin.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insertNote(notes: Notes)

    @Update
    fun updateNote(notes: Notes)

    @Delete
    fun deleteNote(notes: Notes)

    @Query(
        "UPDATE  notes_table SET name = :name, " +
                "description = :description, completed = :completed , " +
                "dateEdited = :dateEdited WHERE id = :noteId "
    )
    fun updateNoteById(
        noteId: Int,
        name: String?,
        description: String?,
        completed: Boolean?,
        dateEdited: String?
    )


    @Query("DELETE FROM NOTES_TABLE WHERE id = :noteId")
    fun deleteNoteById(noteId: Int)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    fun selectNoteById(noteId: Int): LiveData<Notes>

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun selectNotesDesc(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()


}