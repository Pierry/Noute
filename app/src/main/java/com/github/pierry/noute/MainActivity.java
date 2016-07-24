package com.github.pierry.noute;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity {

  @ViewById PagerSlidingTabStrip tabs;
  @ViewById ViewPager pager;

  @Bean(ToolbarBase.class) ToolbarBase toolbarBase;

  @AfterViews void init() {
    SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
    tintManager.setStatusBarTintEnabled(true);
    pager.setAdapter(new HomeAdapter(getActivity().getSupportFragmentManager()));
    tabs.setViewPager(pager);
    tabs.setTextColorResource(R.color.silver);
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
        tv.setTextColor(getResources().getColor(R.color.silver));
      }
    }
  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }

}
