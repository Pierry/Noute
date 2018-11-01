package com.github.pierry.noute.note.di

import android.content.Context
import com.github.pierry.noute.core.data.AppDatabase
import com.github.pierry.noute.note.presentation.interactor.INoteInteractor
import com.github.pierry.noute.note.presentation.interactor.NoteInteractor
import com.github.pierry.noute.note.presentation.presenter.INotePresenter
import com.github.pierry.noute.note.presentation.presenter.NotePresenter
import dagger.Module
import dagger.Provides

@Module
class NoteModule {

  @Provides
  fun providesNotePresenter(noteInteractor: INoteInteractor): INotePresenter = NotePresenter(noteInteractor)

  @Provides
  fun providesNoteInteractor(context: Context): INoteInteractor = NoteInteractor(AppDatabase.getInstance(context)!!)

}