package com.example.jeff.noteskotlin.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.adapter.NoteListAdapter
import com.example.jeff.noteskotlin.model.Notes
import com.example.jeff.noteskotlin.util.SwipeToDelete
import com.example.jeff.noteskotlin.util.hideKeyBoard
import com.example.jeff.noteskotlin.viewmodel.NoteListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.note_list_fragment.*
import java.util.*

class NoteListFragment : Fragment() {


    private val NOTE_LIST_FRAG: String = "NOTELISTFRAGMENT"
    private lateinit var viewModel: NoteListViewModel
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.note_list_fragment, container, false)
        (activity as AppCompatActivity).supportActionBar?.setTitle(getString(R.string.notes_title))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        note_list_recyclerview.layoutManager = LinearLayoutManager(context?.applicationContext)
        noteListAdapter =
                //passes empty arraylist to noteslistadapter
            NoteListAdapter(
                arrayListOf(),
                { notes: Notes -> noteClicked(notes) },
                { notes: Notes -> longPressToComplete(notes) })
        note_list_recyclerview.adapter = noteListAdapter

        viewModel = ViewModelProviders.of(this).get(
            NoteListViewModel::class.java
        )
        //inserts temp test data


        observeLiveData()
        addNote()
        swipeToDelete(context!!)
        hideKeyBoard()


    }

    //Swipe Left to delete function
    private fun swipeToDelete(context: Context) {
        val swipeHandler = object : SwipeToDelete(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val noteSwiped = viewModel.getNotes().value?.get(viewHolder.adapterPosition)
                if (noteSwiped != null) {
                    viewModel.deleteNote(noteSwiped)
                    viewModel.setRecentlyDeletedNote(noteSwiped)
                    undoSnackBar(viewModel.recentlyDeletedNotes.value!!)

                }


            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(note_list_recyclerview)

    }


    private fun longPressToComplete(notes: Notes): Boolean {
        val result = !notes.completed
        viewModel.updateNote(
            notes.id,
            notes.name,
            notes.description,
            result,
            Calendar.getInstance().time.toString()
        )


        Toast.makeText(context, "Note marked: ${toastComplete(result)}", Toast.LENGTH_SHORT).show();
        return true
    }

    private fun toastComplete(result: Boolean): String {
        if (result) {
            return getString(R.string.complete)
        } else {
            return getString(R.string.incomplete)
        }

    }

    private fun noteClicked(notes: Notes) {
        val action = NoteListFragmentDirections.actionNoteListToNoteDetail(notes.id)
        Navigation.findNavController(view!!).navigate(action)

    }

    private fun observeLiveData() {
        viewModel.getNotes().observe(this, Observer {
            noteListAdapter.setNotes(it)

        })

    }

    private fun addNote() {
        add_note_fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(NoteListFragmentDirections.actionNoteListToAddNote())
        }
    }

    private fun undoSnackBar(note: Notes) {
        val snackbar = Snackbar.make(
            note_list_constraint,
            getString(R.string.note_deleted),
            Snackbar.LENGTH_SHORT
        )
        snackbar.setAction(R.string.undo, View.OnClickListener { undoDelete(note) })
        snackbar.show()
        viewModel.clearRecentlyDeletedNote()
    }


    private fun undoDelete(note: Notes) {
        viewModel.insertNote(note)

    }

}

