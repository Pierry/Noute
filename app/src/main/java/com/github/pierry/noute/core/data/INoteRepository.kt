package com.github.pierry.noute.core.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.github.pierry.noute.core.domain.Note

@Dao
interface INoteRepository {

  @Insert(onConflict = REPLACE)
  fun save(note: Note): Long

  @Query("SELECT * FROM Note")
  fun get(): LiveData<List<Note>>

  @Query("SELECT * FROM Note WHERE id = :id")
  fun findById(id: Long): LiveData<Note>

  @Query("DELETE FROM Note WHERE id = :id")
  fun delete(id: Long)

}