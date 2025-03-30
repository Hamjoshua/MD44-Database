package com.example.dbapp

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}

@HiltAndroidApp
class AppModule : Application() {

}