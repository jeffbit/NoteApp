package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes

class NoteAddViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    private val repository: NoteRepository = NoteRepository(application)


    fun insertNote(notes: Notes) {
        repository.insertNotes(notes)
    }


}
