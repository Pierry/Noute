package com.github.pierry.noute.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.github.pierry.noute.ui.fragments.AddNoteFragment;
import com.github.pierry.noute.ui.fragments.NoteFragment;

public class MainAdapter extends FragmentPagerAdapter {

  private final int PAGE_COUNT = 2;
  private String tabTitles[] = new String[] { "Notas", "Adicionar" };

  public MainAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new NoteFragment();
      case 1:
        return new AddNoteFragment();
    }
    return new NoteFragment();
  }

  @Override public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}