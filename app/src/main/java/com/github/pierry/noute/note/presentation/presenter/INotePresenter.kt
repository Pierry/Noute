package com.github.pierry.noute.note.presentation.presenter

import android.arch.lifecycle.LiveData
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.core.presentation.BasePresenter

interface INotePresenter : BasePresenter {

  fun add(content: String)
  fun favorite(id: Long)
  fun watch(): LiveData<List<Note>>
  fun delete(id: Long?)
}