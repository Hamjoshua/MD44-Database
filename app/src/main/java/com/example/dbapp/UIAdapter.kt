package com.example.dbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

interface NoteButtonHandler{
    fun onDeleteClick(note : Note){

    }
}

class NoteAdapter(private val notes: List<Note>,
                  private val buttonHandler: NoteButtonHandler) : ListAdapter<Note,
        NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {
    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById<TextView>(R.id.header_title)
        val content: TextView = view.findViewById<TextView>(R.id.body_content)
        val btn_delete: Button = view.findViewById<Button>(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.r_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.btn_delete.setOnClickListener{
            buttonHandler.onDeleteClick(note)
        }
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title;
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem;
    }
}