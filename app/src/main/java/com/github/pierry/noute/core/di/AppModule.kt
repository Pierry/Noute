package com.github.pierry.noute.core.di

import android.app.Application
import android.content.Context
import com.github.pierry.noute.note.di.NoteComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [
  NoteComponent::class
])
class AppModule {

  @Provides
  fun providesAppContext(application: Application): Context = application.applicationContext

}