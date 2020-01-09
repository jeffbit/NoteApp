package com.example.jeff.noteskotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.adapter.NoteListAdapter
import com.example.jeff.noteskotlin.viewmodel.NoteListViewModel
import kotlinx.android.synthetic.main.note_list_fragment.*

class NoteListFragment : Fragment() {


    private lateinit var viewModel: NoteListViewModel
    private val noteListAdapter = NoteListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.note_list_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        //inserts temp test data
        viewModel.insertTestNotes()





        note_list_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }


    }

    fun observeData() {
        viewModel.notesList.observe(this, Observer { noteList ->
            noteList?.let {
                noteListAdapter.setNotes(noteList)
            }
        })
    }
}
