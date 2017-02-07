package com.github.pierry.noute.ui.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import com.astuetz.PagerSlidingTabStrip;
import com.github.pierry.noute.R;
import com.github.pierry.noute.ui.adapter.MainAdapter;
import com.github.pierry.noute.ui.common.ToolbarBase;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.main_activity) public class MainActivity extends AppCompatActivity
    implements ViewPager.OnPageChangeListener {

  @ViewById PagerSlidingTabStrip tabs;
  @ViewById ViewPager pager;
  @ViewById Toolbar toolbar;

  @Bean(ToolbarBase.class) ToolbarBase toolbarBase;

  private int current = 0;
  private MainAdapter mainAdapter;

  @AfterViews void init() {
    toolbar.setTitle(R.string.app_name);
    setSupportActionBar(toolbar);
    toolbarBase.injectToolbar(toolbar, this);
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    mainAdapter = new MainAdapter(getSupportFragmentManager());
    pager.setAdapter(mainAdapter);
    tabs.setViewPager(pager);
    tabs.setTextColorResource(R.color.nt_silver);
    tabs.setDividerColor(android.R.color.transparent);
    final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
        getResources().getDisplayMetrics());
    pager.setPageMargin(pageMargin);
    pager.setCurrentItem(0);
    toolbarBase.changeColor(tabs, tintManager, getResources().getColor(R.color.colorPrimary));
    tabs.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    switch (position) {
      case 0:
        getSupportActionBar().setTitle(getResources().getString(R.string.notes));
        break;
      case 1:
        getSupportActionBar().setTitle(getResources().getString(R.string.favorites));
        break;
    }
  }

  @Override public void onPageSelected(int position) {
    current = position;
  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
