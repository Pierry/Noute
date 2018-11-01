package com.github.pierry.noute.core.di

import android.support.v4.app.Fragment
import com.github.pierry.noute.note.di.NoteComponent
import com.github.pierry.noute.note.presentation.view.NoteFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewBuilder {

  @Binds
  @IntoMap
  @FragmentKey(NoteFragment::class)
  abstract fun bindNoteFragment(builder: NoteComponent.Builder): AndroidInjector.Factory<out Fragment>

}