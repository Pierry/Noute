package com.github.pierry.noute.note.presentation.model

data class NoteModel(val id: Long?,
                     val translated: String,
                     val timestamp: String,
                     val isLearned: Boolean)