package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(notes: Notes)
    {
        noteDao.insert(notes)
    }

    suspend fun delete(notes: Notes)
    {
        noteDao.delete(notes)
    }
}