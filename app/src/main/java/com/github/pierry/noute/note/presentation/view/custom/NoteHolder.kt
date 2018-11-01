package com.github.pierry.noute.note.presentation.view.custom

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.pierry.noute.R
import com.github.pierry.noute.note.presentation.model.NoteModel
import com.github.pierry.noute.note.presentation.presenter.INotePresenter

class NoteHolder(itemView: View, val presenter: INotePresenter?) : RecyclerView.ViewHolder(itemView) {

  val content = itemView.findViewById(R.id.content) as TextView
  val date = itemView.findViewById(R.id.date) as TextView
  val trash = itemView.findViewById(R.id.trash) as ImageView

  fun bind(note: NoteModel) {
    content.text = note.translated
    date.text = note.timestamp
    trash.setOnClickListener { presenter?.delete(note.id) }
  }

}