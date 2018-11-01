package com.github.pierry.noute.note.presentation.view.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.pierry.noute.R
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.note.presentation.model.mapper.NoteMapper
import com.github.pierry.noute.note.presentation.presenter.INotePresenter

class NoteAdapter : RecyclerView.Adapter<NoteHolder>() {

  var list = ArrayList<Note>()
  var presenter: INotePresenter? = null

  fun addPresenter(presenter: INotePresenter) {
    this.presenter = presenter
  }

  fun addItems(items: List<Note>) {
    list = items as ArrayList<Note>
  }

  override fun getItemCount() = list.count()

  override fun onBindViewHolder(holder: NoteHolder, position: Int) = holder.bind(NoteMapper.map(list[position]))

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder = NoteHolder(LayoutInflater.from(parent.context).
          inflate(R.layout.note_adapter, parent, false), presenter)

}