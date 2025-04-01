package com.example.dbapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        refreshNotes()
        if(notes.value!!.isEmpty()){
            val note: Note = Note(0, "Welcome", "Добро пожаловать!")
            noteRepository.insertNote(note)
        }
    }
    fun refreshNotes() {
        _notes.value = noteRepository.getAllNotes()
    }

    fun insertNote(note: Note) {
        noteRepository.insertNote(note)
        refreshNotes()
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
        refreshNotes()
    }
}