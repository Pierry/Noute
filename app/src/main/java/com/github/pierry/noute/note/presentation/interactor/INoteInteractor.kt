package com.github.pierry.noute.note.presentation.interactor

import android.arch.lifecycle.LiveData
import com.github.pierry.noute.core.domain.Note

interface INoteInteractor {

  fun save(note: Note): Long
  fun findAll(): LiveData<List<Note>>
  fun findById(id: Long): LiveData<Note>
  fun delete(id: Long?)
  fun all(): String = "Heello"

}