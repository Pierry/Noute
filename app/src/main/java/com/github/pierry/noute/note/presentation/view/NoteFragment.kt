package com.github.pierry.noute.note.presentation.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.github.pierry.noute.R
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.core.presentation.BaseFragment
import com.github.pierry.noute.note.presentation.presenter.INotePresenter
import com.github.pierry.noute.note.presentation.view.custom.NoteAdapter
import kotlinx.android.synthetic.main.note_fragment.*
import javax.inject.Inject

class NoteFragment : BaseFragment() {

  @Inject
  lateinit var presenter: INotePresenter

  private lateinit var noteAdapter: NoteAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
          inflater.inflate(R.layout.note_fragment, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setUp()
  }

  override fun onStart() {
    noteAdapter = NoteAdapter()
    noteAdapter.addPresenter(presenter)
    presenter.onStart()
    presenter.watch().observe(this, Observer {
      populate(it)
    })
    super.onStart()
  }

  private fun setUp() {
    add.setOnClickListener { add() }
    config()
    bodyTextChanged()
  }

  private fun add() {
    presenter.add(body.text.toString())
    clearBody()
  }

  private fun clearBody() {
    body.text.clear()
  }

  private fun bodyTextChanged() {
    body.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        if (s.toString().length > 3) {
          showSave()
        } else {
          hideSave()
        }
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
  }

  fun showSave() {
    add.visibility = VISIBLE
  }

  fun hideSave() {
    add.visibility = GONE
  }

  override fun onDestroy() {
    presenter.onPause()
    super.onDestroy()
  }

  private fun config() {
    recycler.layoutManager = LinearLayoutManager(context)
  }

  fun populate(list: List<Note>?) {
    if (list == null) {
      return
    }
    noteAdapter.addItems(list)
    recycler.adapter = noteAdapter
    noteAdapter.notifyDataSetChanged()
  }

}