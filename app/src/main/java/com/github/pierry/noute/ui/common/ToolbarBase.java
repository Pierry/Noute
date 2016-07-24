package com.github.pierry.noute.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.astuetz.PagerSlidingTabStrip;
import com.github.pierry.noute.MainActivity;
import com.github.pierry.noute.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

@EBean public class ToolbarBase {

  private Drawer drawer;
  private Toolbar toolbar;
  private Activity act;
  private AccountHeader headerResult;
  private Context context;

  public ToolbarBase(Context context) {
    this.context = context;
  }

  @UiThread public void injectToolbar(Toolbar toolbar, Activity act) {
    this.toolbar = toolbar;
    this.act = act;
    initAccount();
    initDrawer();
  }

  @UiThread void initDrawer() {
    PrimaryDrawerItem home =
        new PrimaryDrawerItem().withName(R.string.home).withIcon(GoogleMaterial.Icon.gmd_home);
    new DrawerBuilder().withActivity((Activity) act)
        .withToolbar(toolbar)
        .withHasStableIds(true)
        .withTranslucentStatusBar(true)
        .withSelectedItem(-1)
        .withAccountHeader(headerResult)
        .addDrawerItems(home)
        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
          @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            switch (position) {
              case 1:
                context.startActivity(new Intent(context, MainActivity.class));
                return true;
              default:
                return true;
            }
          }
        })
        .build();
  }

  @UiThread void initAccount() {
    headerResult = new AccountHeaderBuilder().withActivity(act)
        .withHeaderBackground(R.color.colorPrimary)
        .addProfiles(new ProfileDrawerItem().withName("Usuário"))
        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
          @Override
          public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
            return false;
          }
        })
        .build();
  }

  @UiThread
  public void changeColor(PagerSlidingTabStrip tabs, SystemBarTintManager tintManager, int newColor) {
    tabs.setBackgroundColor(newColor);
    tintManager.setTintColor(newColor);
  }
}