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

@EFragment(R.layout.view_fragment) public class ViewFragment extends DialogFragment {

  @ViewById Button grid;
  @ViewById Button linear;

  public static String GRID = "grid";
  public static String LINEAR = "linear";

  @Pref MyPrefs_ myPrefs;

  public static ViewFragment_ newInstance() {
    ViewFragment_ f = new ViewFragment_();
    Bundle args = new Bundle();
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
  }

  @Click void grid() {
    myPrefs.edit().view().put(GRID).apply();
    getDialog().dismiss();
    getActivity().recreate();
  }

  @Click void linear() {
    myPrefs.edit().view().put(LINEAR).apply();
    getDialog().dismiss();
    getActivity().recreate();
  }
}
