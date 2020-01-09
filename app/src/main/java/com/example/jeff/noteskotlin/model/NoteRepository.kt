package com.example.jeff.noteskotlin.model

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun retreiveNotesAsc(): LiveData<List<Notes>> {
        return noteDao.selectNotesAsc()
    }

    suspend fun insertNotes(notes: Notes) {
        noteDao.insertNote(notes)
    }

    suspend fun deleteNote(notes: Notes) {
        noteDao.deleteNote(notes)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    suspend fun updateNote(notes: Notes) {
        noteDao.updateNote(notes)
    }

    suspend fun retrieveNoteById(id: Int): LiveData<Notes> {
        return noteDao.selectNoteById(id)
    }


}