package com.github.pierry.noute.note.presentation.model.mapper

import com.github.pierry.noute.core.common.DateHelper
import com.github.pierry.noute.core.domain.Note
import com.github.pierry.noute.note.presentation.model.NoteModel

object NoteMapper {

  fun map(note: Note) = NoteModel(
          id = note.id,
          translated = note.translated,
          isLearned = note.isLearned,
          timestamp = DateHelper.convert(note.timestamp)
  )

}