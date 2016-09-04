package com.github.pierry.noute;

import android.support.multidex.MultiDex;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.jakewharton.threetenabp.AndroidThreeTen;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class NouteApp extends com.activeandroid.app.Application {

  @Override public void onCreate() {
    super.onCreate();
    Fabric.with(this, new Crashlytics());
    MultiDex.install(this);
    AndroidThreeTen.init(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new CrashReportingTree());
    }
  }

  private static class CrashReportingTree extends Timber.Tree {
    @Override protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return;
      }
      Crashlytics.log(priority, tag, message);
      if (t != null) {
        if (priority == Log.ERROR) {
          Crashlytics.logException(t);
        } else if (priority == Log.WARN) {
          Crashlytics.logException(t);
        }
      }
    }
  }
}
