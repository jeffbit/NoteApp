package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository = NoteRepository(application)



    fun getNotes() = repository.retrieveNotesDesc()


    fun deleteNote(notes: Notes) {
        repository.deleteNote(notes)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }




}
