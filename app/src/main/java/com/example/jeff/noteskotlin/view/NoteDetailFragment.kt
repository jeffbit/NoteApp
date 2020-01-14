package com.example.jeff.noteskotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.util.DETAIL_NOTE_TITLE
import com.example.jeff.noteskotlin.viewmodel.NoteDetailViewModel
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {


    private lateinit var viewModel: NoteDetailViewModel
    private var argumentId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.note_detail_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)

        observeViewModel()

        updateNote()
        shareNote()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(DETAIL_NOTE_TITLE, note_title_tv.text.toString())
    }

    private fun observeViewModel() {
        arguments?.let {
            argumentId = NoteDetailFragmentArgs.fromBundle(it).noteIdArgument
        }
        viewModel.getClickedNote(argumentId).observe(this, Observer { notes ->
            notes?.let {
                note_title_tv.setText(notes.name, TextView.BufferType.EDITABLE)
                note_description_tv.setText(notes.description, TextView.BufferType.EDITABLE)
                checkboxClicked(notes.completed)
            }

        })
    }


    private fun updateNote() {
        update_notes_sb.setOnClickListener {
            val name = note_title_tv.text.toString()
            val description = note_description_tv.text.toString()
            val completed = checkBoxReturnValue()
            viewModel.updateNote(argumentId, name, description, completed)
            viewModel.returnToNoteList(it)
        }
    }


    private fun shareNote() {
        share_note_fab.setOnClickListener {

            startActivity(
                viewModel.shareNote(
                    note_title_tv.text.toString(),
                    note_description_tv.text.toString()
                )
            )
        }

    }

    private fun checkboxClicked(value: Boolean) {
        note_complete_cb.isChecked = value
    }


    private fun checkBoxReturnValue(): Boolean {
        return note_complete_cb.isChecked

    }


    private fun hideKeyBoard() {
        note_description_tv.clearFocus()
        note_title_tv.clearFocus()
        InputMethodManager.HIDE_IMPLICIT_ONLY

    }


}
