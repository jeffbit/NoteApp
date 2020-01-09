package com.example.jeff.noteskotlin.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.viewmodel.NoteDetailViewModel

class NoteDetailFragment : Fragment() {



    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }


}
