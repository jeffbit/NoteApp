package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes
import java.util.*

class NoteDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository = NoteRepository(application)

    fun getClickedNote(id: Int): LiveData<Notes> {
        return repository.getNoteById(id)

    }


    fun updateNote(id: Int, name: String?, description: String?, completed: Boolean) {
        val currentTime = Calendar.getInstance().time.toString()
        repository.updateNoteById(id, name, description, completed, currentTime)
    }


}
