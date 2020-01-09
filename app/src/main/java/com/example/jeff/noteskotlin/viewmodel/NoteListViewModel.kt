package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jeff.noteskotlin.model.NoteDatabase
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository

    //notes
    private val _notesList = MutableLiveData<List<Notes>>()
    val notesList: LiveData<List<Notes>>
        get() = _notesList

    //no notes displayed
    private val _noNotesDisplayed = MutableLiveData<Boolean>()
    val noNotesDisplayed: LiveData<Boolean>
        get() = _noNotesDisplayed


    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        viewModelScope.launch {
            _notesList.postValue(repository.retreiveNotesAsc().value)

        }
        emptyNotes()


    }

    fun insertTestNotes() {
        val note1 = Notes(0, "note1", "note description")
        val note2 = Notes(0, "note2", "note description")
        val note3 = Notes(0, "note3", "note description")

        insertNotes(note1)
        insertNotes(note2)
        insertNotes(note3)

    }


    private fun insertNotes(notes: Notes) {
        viewModelScope.launch {
            repository.insertNotes(notes)
        }
        _noNotesDisplayed.value = false

    }

    private fun deleteNote(notes: Notes) {
        viewModelScope.launch {
            repository.deleteNote(notes)
        }
    }

    private fun deleteAllNotes() {
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }


    private fun emptyNotes() {
        if (_notesList.value?.isEmpty()!!) {
            _noNotesDisplayed.value = true
        } else {
            _noNotesDisplayed.value = false
        }
    }


}
