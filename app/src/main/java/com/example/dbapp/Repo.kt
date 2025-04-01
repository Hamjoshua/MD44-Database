package com.example.dbapp

import androidx.lifecycle.LiveData
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    fun getAllNotes() : LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}