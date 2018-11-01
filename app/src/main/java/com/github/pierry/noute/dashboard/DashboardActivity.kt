package com.github.pierry.noute.dashboard

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.pierry.noute.R
import com.github.pierry.noute.note.presentation.view.NoteFragment
import kotlinx.android.synthetic.main.dashboard_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class DashboardActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.dashboard_activity)
    notes()
    navigation.setOnNavigationItemSelectedListener(this)
  }

  override fun onStart() {
    changeTitle()
    super.onStart()
  }

  fun changeTitle() {
    topTitle.text = getString(R.string.notes)
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.actionNotes -> {
        navigation.getMenu().getItem(0).isChecked = true
        notes()
      }
      R.id.actionFavs -> {
        navigation.getMenu().getItem(1).isChecked = true

      }
    }
    return false
  }

  internal fun notes() {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.frame, NoteFragment())
    transaction.commit()
  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }

}