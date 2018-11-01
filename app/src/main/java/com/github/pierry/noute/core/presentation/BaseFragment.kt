package com.github.pierry.noute.core.presentation

import android.app.Activity
import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import com.github.pierry.noute.R
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.view_loader.*
import kotlinx.android.synthetic.main.view_no_content.*
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment : DaggerFragment(), BaseView {

  override fun onAttach(activity: Activity?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(activity)
  }

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun showLoader() {
    progressBar.visibility = VISIBLE;
  }

  override fun hideLoader() {
    progressBar.visibility = GONE
  }

  override fun showNoContent() {
    noContent.visibility = VISIBLE
  }

  override fun hideNoContent() {
    noContent.visibility = GONE
  }

  override fun showNoInternet() {
    toast(getString(R.string.no_internet))
  }

  override fun showToast(msg: String) {
    toast(msg)
  }

}