package com.example.jeff.noteskotlin.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NoteRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val noteDao: NoteDao

    private var notes: LiveData<List<Notes>>


    private val _note = MutableLiveData<Notes>()
    val note: LiveData<Notes>
        get() = _note

    init {
        val database = NoteDatabase.getDatabase(application.applicationContext)!!
        noteDao = database.noteDao()
        notes = noteDao.selectNotesDesc()

    }


    fun getNoteById(id: Int) = noteDao.selectNoteById(id)


    fun retrieveNotesDesc() = notes

    fun insertNotes(notes: Notes) {
        launch {
            insertNotesBG(notes)
        }
    }

    fun deleteNote(notes: Notes) {
        launch {
            deleteNoteBG(notes)
        }
    }

    fun deleteNoteById(id: Int) {
        launch {
            deleteNoteByIdBG(id)
        }
    }

    fun deleteAllNotes() {
        launch {
            deleteAllNotesBG()
        }
    }

    fun updateNote(notes: Notes) {
        launch {
            updateNoteBG(notes)
        }
    }

    fun updateNoteById(
        id: Int,
        name: String?,
        description: String?,
        completed: Boolean,
        dateEdited: String?
    ) {
        launch {
            updateNotesByIdBG(id, name, description, completed, dateEdited)
        }
    }


    private suspend fun insertNotesBG(notes: Notes) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(notes)

        }
    }

    private suspend fun deleteNoteBG(notes: Notes) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(notes)
        }
    }

    private suspend fun deleteNoteByIdBG(id: Int) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNoteById(id)
        }
    }

    private suspend fun deleteAllNotesBG() {
        withContext(Dispatchers.IO) {
            noteDao.deleteAllNotes()
        }
    }

    private suspend fun updateNotesByIdBG(
        id: Int,
        name: String?,
        description: String?,
        completed: Boolean,
        dateEdited: String?
    ) {
        withContext(Dispatchers.IO) {
            noteDao.updateNoteById(id, name, description, completed, dateEdited)
        }
    }

    private suspend fun updateNoteBG(notes: Notes) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(notes)
        }
    }


}