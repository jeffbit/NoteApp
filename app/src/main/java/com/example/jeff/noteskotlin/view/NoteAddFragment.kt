package com.example.jeff.noteskotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.util.hideKeyBoard
import com.example.jeff.noteskotlin.viewmodel.NoteAddViewModel
import kotlinx.android.synthetic.main.note_add_fragment.*

class NoteAddFragment : Fragment() {

    private lateinit var viewModel: NoteAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle(getString(R.string.add_note_title))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_add_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteAddViewModel::class.java)



        save_note_btn.setOnClickListener {
            saveNote()
            Navigation.findNavController(view)
                .navigate(NoteAddFragmentDirections.actionAddNoteToNoteList())
            clearNote()
            hideKeyBoard()

        }

        cancel_note_btn.setOnClickListener {
            clearNote()
            Navigation.findNavController(view)
                .navigate(NoteAddFragmentDirections.actionAddNoteToNoteList())
            hideKeyBoard()
        }
    }


    private fun saveNote() {
        viewModel.saveNote(
            add_notetitle_et.text.toString(),
            add_notedescription_et.text.toString()
        )
    }


    private fun clearNote() {
        add_notetitle_et.setText("")
        add_notedescription_et.setText("")
    }
}
