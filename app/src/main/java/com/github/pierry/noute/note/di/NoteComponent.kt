package com.github.pierry.noute.note.di

import com.github.pierry.noute.note.presentation.view.NoteFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
  NoteModule::class,
  NoteModuleView::class
])
interface NoteComponent : AndroidInjector<NoteFragment> {

  @Subcomponent.Builder
  abstract class Builder() : AndroidInjector.Builder<NoteFragment>()

}