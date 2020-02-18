package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository = NoteRepository(application)

    //clicked note
    private val _noteClicked = MutableLiveData<Notes>()
    val noteclicked: LiveData<Notes>
        get() = _noteClicked

    private val _recentlyDeletedNote = MutableLiveData<Notes>()
    val recentlyDeletedNotes: LiveData<Notes>
        get() = _recentlyDeletedNote


    fun setRecentlyDeletedNote(notes: Notes) {
        _recentlyDeletedNote.value = notes

    }

    fun updateNote(
        id: Int,
        name: String?,
        description: String?,
        complete: Boolean?,
        datedEdited: String?
    ) {
        repository.updateNoteById(id, name, description, complete, datedEdited)
    }

    fun clearRecentlyDeletedNote() {
        _recentlyDeletedNote.value = null
    }


    fun getNotes() = repository.retrieveNotesDesc()


    fun deleteNote(notes: Notes) {
        repository.deleteNote(notes)
    }


    fun insertNote(notes: Notes) {
        repository.insertNotes(notes)
    }


}
