package com.example.dbapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, NoteDatabase::class.java, "NoteDatabase"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    @Singleton
    @Provides
    fun provideUserDao(db: NoteDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
}

@HiltAndroidApp
class AppModule : Application() {

}