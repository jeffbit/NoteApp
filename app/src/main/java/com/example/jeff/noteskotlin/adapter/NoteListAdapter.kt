package com.example.jeff.noteskotlin.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.adapter.NoteListAdapter.NoteListViewHolder
import com.example.jeff.noteskotlin.model.Notes
import com.example.jeff.noteskotlin.view.NoteListFragmentDirections

class NoteListAdapter(private var noteList: List<Notes>) :
    RecyclerView.Adapter<NoteListViewHolder>() {


    fun setNotes(notes: List<Notes>) {
        this.noteList = notes
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val infalter =
            LayoutInflater.from(parent.context)
        val view = infalter.inflate(R.layout.note_list_item, parent, false)

        return NoteListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = noteList[position]
        holder.noteTitle.text = note.name
        holder.noteDescription.text = note.description
        holder.itemView.setOnClickListener {
            //navigates to NoteDetailFragment on click and passes Note.id primary key
            val action = NoteListFragmentDirections.actionNoteListToNoteDetail(note.id)
            Navigation.findNavController(holder.itemView)
                .navigate(action)
        }

        if (note.completed == true) {
            holder.noteTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.noteDescription.visibility = View.GONE
        } else {
            holder.noteTitle.paintFlags = 0
            holder.noteDescription.visibility = View.VISIBLE

        }
    }


    inner class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.item_notetitle_tv)
        val noteDescription: TextView = itemView.findViewById(R.id.item_notedescription_textview)


    }


}