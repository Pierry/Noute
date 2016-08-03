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
  private Context context;

  public ToolbarBase(Context context) {
    this.context = context;
  }

  @UiThread public void injectToolbar(Toolbar toolbar, Activity act) {
    this.toolbar = toolbar;
    this.act = act;
    initDrawer();
  }

  @UiThread void initDrawer() {
    PrimaryDrawerItem sugests =
        new PrimaryDrawerItem().withName(R.string.sugests).withIcon(GoogleMaterial.Icon.gmd_email);
    PrimaryDrawerItem share =
        new PrimaryDrawerItem().withName(R.string.share).withIcon(GoogleMaterial.Icon.gmd_share);
    PrimaryDrawerItem about =
        new PrimaryDrawerItem().withName(R.string.about).withIcon(GoogleMaterial.Icon.gmd_info);
    new DrawerBuilder().withActivity((Activity) act)
        .withToolbar(toolbar)
        .
        .withHasStableIds(true)
        .withTranslucentStatusBar(true)
        .withSelectedItem(-1)
        .addDrawerItems(sugests, share, about)
        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
          @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            switch (position) {
              case 1:
                context.startActivity(new Intent(context, MainActivity.class));
                return true;
              case 2:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Utilize o app Noute - disponível na Google Play";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                    "Indicação de app - Noute");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Compartilhar "));
                return true;
              default:
                return true;
            }
          }
        })
        .build();
  }

  @UiThread public void changeColor(PagerSlidingTabStrip tabs, SystemBarTintManager tintManager,
      int newColor) {
    tabs.setBackgroundColor(newColor);
    tintManager.setTintColor(newColor);
  }
}