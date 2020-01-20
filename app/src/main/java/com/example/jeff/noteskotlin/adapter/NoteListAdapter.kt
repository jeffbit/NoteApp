package com.example.jeff.noteskotlin.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jeff.noteskotlin.R
import com.example.jeff.noteskotlin.adapter.NoteListAdapter.NoteListViewHolder
import com.example.jeff.noteskotlin.model.Notes

class NoteListAdapter(
    private var noteList: List<Notes>,
    private val clickListener: (Notes) -> Unit,
    private val longClickListener: (Notes) -> Boolean
) :
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

        holder.bind(note, clickListener, longClickListener)
        noteCompleted(note, holder)

    }


    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.item_notetitle_tv)
        val noteDescription: TextView = itemView.findViewById(R.id.item_notedescription_tv)
        val noteDate: TextView = itemView.findViewById(R.id.item_notedate_tv)


        fun bind(
            note: Notes,
            clickListener: (Notes) -> Unit,
            longClickListener: (Notes) -> Boolean
        ) {
            noteTitle.text = note.name
            noteDescription.text = note.description
            noteDate.text = note.dateEdited
            //passes clicklistener into bind
            itemView.setOnClickListener { clickListener(note) }
            itemView.setOnLongClickListener { longClickListener(note) }


        }


    }

    private fun noteCompleted(note: Notes, holder: NoteListViewHolder) {

        if (note.completed == true) {
            holder.noteTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.noteDescription.visibility = View.GONE
        } else {
            holder.noteTitle.paintFlags = 0
            holder.noteDescription.visibility = View.VISIBLE

        }

    }


}

