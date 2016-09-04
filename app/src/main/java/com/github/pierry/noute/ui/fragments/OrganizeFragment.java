package com.github.pierry.noute.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.Button;
import com.github.pierry.noute.R;
import com.github.pierry.noute.common.MyPrefs_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EFragment(R.layout.organize_fragment) public class OrganizeFragment extends DialogFragment {

  @ViewById Button byDate;
  @ViewById Button byColor;
  @ViewById Button byModified;

  public static String BY_DATE = "bydate";
  public static String BY_COLOR = "bycolor";
  public static String BY_MODIFIED = "bymodified";

  @Pref MyPrefs_ myPrefs;

  public static OrganizeFragment_ newInstance() {
    OrganizeFragment_ f = new OrganizeFragment_();
    Bundle args = new Bundle();
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
  }

  @Click void byDate() {
    myPrefs.edit().organize().put(BY_DATE).apply();
    getDialog().dismiss();
    getActivity().recreate();
  }

  @Click void byColor() {
    myPrefs.edit().organize().put(BY_COLOR).apply();
    getDialog().dismiss();
    getActivity().recreate();
  }

  @Click void byModified() {

  }
}
