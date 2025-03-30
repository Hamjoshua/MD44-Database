package com.example.dbapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String
)

@Dao
interface NoteDao {
    @Query("Select * from notes")
    fun getAllNotes() : List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}