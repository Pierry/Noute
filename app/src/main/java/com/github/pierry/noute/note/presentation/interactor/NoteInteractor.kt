package com.github.pierry.noute.note.presentation.interactor

import android.arch.lifecycle.LiveData
import com.github.pierry.noute.core.data.AppDatabase
import com.github.pierry.noute.core.domain.Note

class NoteInteractor(val db: AppDatabase) : INoteInteractor {

  override fun findAll(): LiveData<List<Note>> = db.noteRepository().get()

  override fun save(note: Note): Long = db.noteRepository().save(note)

  override fun findById(id: Long): LiveData<Note> = db.noteRepository().findById(id)

  override fun delete(id: Long?) = db.noteRepository().delete(id!!)

}