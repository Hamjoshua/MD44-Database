package com.example.dbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter() : ListAdapter<Note, RecyclerView.ViewHolder>(NoteDiffCallback()) {
    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById<TextView>(R.id.header_title)
        val content: TextView = view.findViewById<TextView>(R.id.body_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.r_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
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