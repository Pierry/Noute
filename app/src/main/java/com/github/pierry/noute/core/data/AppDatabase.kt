package com.github.pierry.noute.core.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.pierry.noute.core.domain.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun noteRepository(): INoteRepository

  companion object {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase? {
      if (INSTANCE == null) {
        synchronized(AppDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                  AppDatabase::class.java, "nouteapp3.db")
                  .build()
        }
      }
      return INSTANCE
    }

    fun destroyInstance() {
      INSTANCE = null
    }
  }


}