package com.example.jeff.noteskotlin.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.util.hideKeyBoard
import com.example.jeff.noteskotlin.viewmodel.NoteDetailViewModel
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {


    private lateinit var viewModel: NoteDetailViewModel
    private var argumentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.note_details_title)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)

        arguments?.let {
            argumentId = NoteDetailFragmentArgs.fromBundle(it).noteIdArgument
        }

        if (viewModel.dataObserved.value != true) {
            observeViewModel()
        }

        updateNote()
        shareNote()

    }


    private fun observeViewModel() {
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
            returnToNoteList(it)
            hideKeyBoard()
        }
    }


    private fun shareNote() {
        share_note_fab.setOnClickListener {

            shareNote(
                note_title_tv.text.toString(),
                note_description_tv.text.toString()
            )
        }

    }

    private fun returnToNoteList(view: View) {
        val action = NoteDetailFragmentDirections.actionNoteDetailToNoteList()
        Navigation.findNavController(view).navigate(action)
    }


    private fun checkboxClicked(value: Boolean) {
        note_complete_cb.isChecked = value
    }


    private fun checkBoxReturnValue(): Boolean {
        return note_complete_cb.isChecked

    }

    private fun shareNote(title: String, description: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_intent, title, description))
        }

        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)

    }


}
