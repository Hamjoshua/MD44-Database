package com.example.dbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels<NoteViewModel>()
    lateinit var binding : ActivityMainBinding
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
            val note : Note = Note(0, title, content)
            viewModel.insertNote(note)
        }
    }

    fun initList(){
        val notes = viewModel.getAllNotes()
        binding.rView.layoutManager = LinearLayoutManager(this)
        binding.rView.adapter = NoteAdapter(notes)
    }
}