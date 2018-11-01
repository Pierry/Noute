package com.github.pierry.noute.favorite.di

import com.github.pierry.noute.favorite.presentation.presenter.FavPresenter
import com.github.pierry.noute.favorite.presentation.presenter.IFavPresenter
import com.github.pierry.noute.favorite.presentation.view.IFavView
import dagger.Module
import dagger.Provides

@Module
class FavModule {

  @Provides
  fun providesFavPresenter(favView: IFavView): IFavPresenter = FavPresenter(favView)

}