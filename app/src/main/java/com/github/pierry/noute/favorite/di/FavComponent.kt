package com.github.pierry.noute.favorite.di

import com.github.pierry.noute.favorite.presentation.view.FavFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
  FavModule::class
])
interface FavComponent : AndroidInjector<FavFragment> {

  @Subcomponent.Builder
  abstract class Builder() : AndroidInjector.Builder<FavFragment>()

}