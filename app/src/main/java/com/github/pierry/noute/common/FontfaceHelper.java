package com.github.pierry.noute.common;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontfaceHelper {

  public static void setFontFace(Context context, TextView textView) {
    Typeface type = Typeface.createFromAsset(context.getAssets(), "droidsans.ttf");
    textView.setTypeface(type);
  }
}
