package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes
import java.util.*


class NoteDetailViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository: NoteRepository = NoteRepository(application)
    private val _dataObserved = MutableLiveData<Boolean>(false)
    val dataObserved: LiveData<Boolean>
        get() = _dataObserved


    fun getClickedNote(id: Int): LiveData<Notes> {
        _dataObserved.postValue(true)
        return repository.getNoteById(id)


    }

    fun updateNote(id: Int, name: String?, description: String?, completed: Boolean) {
        val currentTime = Calendar.getInstance().time.toString()
        repository.updateNoteById(id, name, description, completed, currentTime)
    }


}
