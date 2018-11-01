package com.github.pierry.noute.core.common

import android.graphics.Color

object ColorHelper {

  fun convert(color: String): Int {
    return Color.parseColor(color)
  }
}