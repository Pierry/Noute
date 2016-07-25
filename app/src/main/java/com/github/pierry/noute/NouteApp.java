package com.github.pierry.noute;

import android.support.multidex.MultiDex;

public class NouteApp extends com.activeandroid.app.Application {

  @Override public void onCreate() {
    super.onCreate();
    MultiDex.install(this);
  }
}
