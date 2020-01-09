package com.example.jeff.noteskotlin.adapter

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

class NoteListAdapter(val noteList: ArrayList<Notes>) : RecyclerView.Adapter<NoteListViewHolder>() {

    fun setNotes(notes: List<Notes>) {
        this.noteList.clear()
        noteList.addAll(notes)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)

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
            Navigation.findNavController(holder.itemView)
                .navigate(NoteListFragmentDirections.actionNoteListToNoteDetail(note.id))
        }
    }

    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.item_notetitle_tv)
        val noteDescription: TextView = itemView.findViewById(R.id.item_notedescription_textview)


    }
}