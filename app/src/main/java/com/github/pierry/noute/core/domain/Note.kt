package com.github.pierry.noute.core.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id: Long?,
                var title: String,
                var translated: String,
                val timestamp: Long,
                var isLearned: Boolean) {
  fun learned() {
    isLearned = true
  }
}