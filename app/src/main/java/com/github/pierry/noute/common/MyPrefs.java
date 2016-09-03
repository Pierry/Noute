package com.github.pierry.noute.common;

import com.github.pierry.noute.ui.fragments.OrganizeFragment;
import com.github.pierry.noute.ui.fragments.OrganizeFragment_;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref public interface MyPrefs {

  @DefaultString("bydate") String organize();

  @DefaultString("linear") String view();
}
