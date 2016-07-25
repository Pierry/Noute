package com.github.pierry.noute.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.github.pierry.noute.R;
import com.github.pierry.noute.ui.fragments.FavFragment_;
import com.github.pierry.noute.ui.fragments.NoteFragment;
import com.github.pierry.noute.ui.fragments.NoteFragment_;

public class MainAdapter extends FragmentPagerAdapter
    implements PagerSlidingTabStrip.CustomTabProvider {

  private final int PAGE_COUNT = 2;

  public MainAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new NoteFragment_();
      case 1:
        return new FavFragment_();
    }
    return new NoteFragment();
  }

  @Override public CharSequence getPageTitle(int position) {
    return null;
  }

  @Override public View getCustomTabView(ViewGroup parent, int position) {
    switch (position) {
      case 0:
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_notes, null);
      case 1:
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_favs, null);
      default:
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_notes, null);
    }
  }

  @Override public void tabSelected(View tab) {

  }

  @Override public void tabUnselected(View tab) {

  }
}