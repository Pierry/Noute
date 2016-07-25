package com.github.pierry.noute;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.astuetz.PagerSlidingTabStrip;
import com.github.pierry.noute.ui.adapter.MainAdapter;
import com.github.pierry.noute.ui.common.ToolbarBase;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity
    implements ViewPager.OnPageChangeListener {

  @ViewById PagerSlidingTabStrip tabs;
  @ViewById ViewPager pager;
  @ViewById Toolbar toolbar;

  @Bean(ToolbarBase.class) ToolbarBase toolbarBase;

  @AfterViews void init() {
    toolbar.setTitle(R.string.app_name);
    setSupportActionBar(toolbar);
    toolbarBase.injectToolbar(toolbar, this);
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    pager.setAdapter(new MainAdapter(getSupportFragmentManager()));
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
    LinearLayout layout = (LinearLayout) tabs.getChildAt(0);
    for (int i = 0; i < layout.getChildCount(); i++) {
      TextView tv = (TextView) layout.getChildAt(i);
      if (i == position) {
        tv.setTextColor(getResources().getColor(R.color.icons));
      } else {
        tv.setTextColor(getResources().getColor(R.color.nt_silver));
      }
    }
  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
