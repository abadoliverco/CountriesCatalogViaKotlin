package com.oliver.countriescatalogviakotlin.di.module

import android.content.Context
import com.oliver.countriescatalogviakotlin.di.scope.PerActivity
import com.oliver.countriescatalogviakotlin.mvp.main.details.CountryDetailsPresenter
import com.oliver.countriescatalogviakotlin.mvp.main.list.CountryListPresenter
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

/**
 * Created by oliverabad on 21/7/18.
 */

@Module
class PresenterModule(val context: Context) : WeakReference<Context>(context) {

    @Provides
    @PerActivity
    fun providesCountryListPresenter(): CountryListPresenter {
        return CountryListPresenter(context)
    }

    @Provides
    @PerActivity
    fun providesCountryDetailsPresenter(): CountryDetailsPresenter {
        return CountryDetailsPresenter(context)
    }
}