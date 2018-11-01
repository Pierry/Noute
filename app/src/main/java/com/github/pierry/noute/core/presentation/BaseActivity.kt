package com.github.pierry.noute.core.presentation

import android.content.Context
import android.view.View
import com.github.pierry.noute.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.view_loader.*
import kotlinx.android.synthetic.main.view_no_content.*
import org.jetbrains.anko.toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

  override fun onAttachedToWindow() {
    AndroidInjection.inject(this)
    super.onAttachedToWindow()
  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }

  override fun showLoader() {
    progressBar.visibility = View.VISIBLE;
  }

  override fun hideLoader() {
    progressBar.visibility = View.GONE
  }

  override fun showNoContent() {
    noContent.visibility = View.VISIBLE
  }

  override fun hideNoContent() {
    noContent.visibility = View.GONE
  }

  override fun showNoInternet() {
    toast(getString(R.string.no_internet))
  }

  override fun showToast(msg: String) {
    toast(msg)
  }

}