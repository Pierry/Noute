package com.github.pierry.noute.note

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnitRunner
import com.github.pierry.noute.core.data.AppDatabase
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.note.presentation.interactor.INoteInteractor
import com.github.pierry.noute.note.presentation.interactor.NoteInteractor
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class NoteInteractorTest : AndroidJUnitRunner() {

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private lateinit var noteInteractor: INoteInteractor
  private lateinit var appDatabase: AppDatabase

  @Before
  fun setUp() {
    val context: Context = InstrumentationRegistry.getTargetContext()
    appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    noteInteractor = NoteInteractor(appDatabase)
  }

  @Test
  fun findAll_NotEmpty() {
    Mockito.`when`(noteInteractor.findAll()).thenReturn(getList())
    val all = noteInteractor.findAll()
    assertEquals(all, getList())
  }

  @After
  fun tearDown() {
    appDatabase.close()
  }

  private fun getList(): MutableLiveData<List<Note>> {
    val mutableLiveData = MutableLiveData<List<Note>>()
    mutableLiveData.value = listOf(Note(1, "Ola", "Hello", 0, true))
    return mutableLiveData
  }

  private fun getEmptyList(): MutableLiveData<List<Note>> {
    return MutableLiveData<List<Note>>()
  }

}