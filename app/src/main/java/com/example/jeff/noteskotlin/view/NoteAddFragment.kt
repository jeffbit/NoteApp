package com.example.jeff.noteskotlin.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.viewmodel.NoteAddViewModel

class NoteAddFragment : Fragment() {

    private lateinit var viewModel: NoteAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_add_fragment, container, false)
    }



}
