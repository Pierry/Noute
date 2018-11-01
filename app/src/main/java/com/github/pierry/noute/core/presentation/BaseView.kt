package com.github.pierry.noute.core.presentation

interface BaseView {

  fun showLoader()

  fun hideLoader()

  fun showNoContent()

  fun hideNoContent()

  fun showNoInternet()

  fun showToast(msg: String)

}