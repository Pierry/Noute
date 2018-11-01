package com.github.pierry.noute.core.common

import org.threeten.bp.Instant
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

  private const val PATTERN = "dd', 'MMM' - 'HH:mm"

  fun convert(timestamp: Long): String {
    val parsedDate = Date(timestamp)
    val dateFormat = SimpleDateFormat(PATTERN, Locale.getDefault())
    return dateFormat.format(parsedDate)
  }

  fun now(): Long = Instant.now().toEpochMilli()

}