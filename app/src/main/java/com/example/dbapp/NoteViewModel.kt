package com.example.dbapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    init {
        val note: Note = Note(0, "Welcome", "Добро пожаловать!")
        noteRepository.insertNote(note)
    }
    fun getAllNotes() : List<Note> {
        return noteRepository.getAllNotes()
    }

    fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}