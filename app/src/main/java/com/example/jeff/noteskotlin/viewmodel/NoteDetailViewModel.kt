package com.example.jeff.noteskotlin.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.jeff.noteskotlin.model.NoteRepository
import com.example.jeff.noteskotlin.model.Notes
import com.example.jeff.noteskotlin.util.DETAIL_NOTE_DESC
import com.example.jeff.noteskotlin.util.DETAIL_NOTE_TITLE
import com.example.jeff.noteskotlin.view.NoteDetailFragmentDirections
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

    fun returnToNoteList(view: View) {
        val action = NoteDetailFragmentDirections.actionNoteDetailToNoteList()
        Navigation.findNavController(view).navigate(action)
    }


    fun shareNote(title: String, description: String): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(DETAIL_NOTE_TITLE, title)
        intent.putExtra(DETAIL_NOTE_DESC, description)

        val shareIntent = Intent.createChooser(intent, null)
        return shareIntent

    }


}
