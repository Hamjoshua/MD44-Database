package com.example.dbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteButtonHandler {
    private val viewModel: NoteViewModel by viewModels<NoteViewModel>()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeNotesList()
        initList()
        initAddButton()
    }

    override fun onDeleteClick(note: Note) {
        viewModel.deleteNote(note)
    }

    fun initAddButton(){
        binding.addBtn.setOnClickListener{
            val title : String = binding.title.text.toString()
            val content : String = binding.content.text.toString()
            val note : Note = Note(0, title, content)
            viewModel.insertNote(note)

            Toast.makeText(this, "Заметка добавлена", Toast.LENGTH_SHORT).show()
            updateList()
        }
    }

    fun initList(){
        adapter = NoteAdapter(viewModel.notes.value!!, this)
        binding.rView.layoutManager = LinearLayoutManager(this)
        binding.rView.adapter = adapter

        updateList()
    }

    fun updateList(){
        adapter.submitList(viewModel.notes.value)
    }

    private fun observeNotesList() {
        viewModel.notes.observe(this) { notes ->
            notes?.let {
                updateList()
            }
        }
    }
}