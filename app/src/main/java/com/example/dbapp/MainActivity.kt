package com.example.dbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels<NoteViewModel>()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        initAddButton()
    }

    fun initAddButton(){
        binding.addBtn.setOnClickListener{
            val title : String = binding.title.text.toString()
            val content : String = binding.content.text.toString()
            val note : Note = Note(1, title, content)
            viewModel.insertNote(note)

            Toast.makeText(this, "Заметка добавлена", Toast.LENGTH_SHORT).show()
            initList()
        }
    }

    fun initList(){
        val notes = viewModel.getAllNotes()

        if(notes.isNotEmpty()){
            adapter = NoteAdapter(notes)
            binding.rView.layoutManager = LinearLayoutManager(this)
            binding.rView.adapter = adapter
        }

    }
}