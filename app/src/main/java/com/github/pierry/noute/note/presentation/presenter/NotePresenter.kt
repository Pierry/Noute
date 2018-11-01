package com.github.pierry.noute.note.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.pierry.noute.core.common.DateHelper
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.note.presentation.interactor.INoteInteractor
import org.jetbrains.anko.doAsync

class NotePresenter(val noteInteractor: INoteInteractor) : INotePresenter {

  override fun onStart() {

  }

  override fun watch(): LiveData<List<Note>> = noteInteractor.findAll()

  override fun delete(id: Long?) {
    doAsync { noteInteractor.delete(id) }
  }

  override fun onPause() {

  }

  override fun favorite(id: Long) {
    val mutableData = MutableLiveData<Note>()
    doAsync {
      val note = noteInteractor.findById(id)
      note.value?.learned()
      noteInteractor.save(note.value!!)
    }
  }

  override fun add(content: String) {
    val note = Note(null, content, content, DateHelper.now(), false)
    doAsync {
      noteInteractor.save(note)
    }
  }

}