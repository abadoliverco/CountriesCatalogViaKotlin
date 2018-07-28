package com.oliver.countriescatalogviakotlin.di.component

import android.content.Context
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.data.DataManager
import com.oliver.countriescatalogviakotlin.di.module.ApplicationModule
import com.oliver.countriescatalogviakotlin.mvp.base.BasePresenterInjection
import com.oliver.countriescatalogviakotlin.mvp.injectable.InjectableActivity
import com.oliver.countriescatalogviakotlin.mvp.main.details.CountryDetailsPresenter
import com.oliver.countriescatalogviakotlin.mvp.main.list.CountryListPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oliverabad on 21/7/18.
 */

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {


    fun getContext(): Context

    fun getDataManager(): DataManager

    fun inject(application: CountriesCatalogApplication)

    fun inject(basePresenterInjection: BasePresenterInjection)

    fun inject(injectableActivity: InjectableActivity)

    fun inject(countriesListPresenter: CountryListPresenter)

    fun inject(countryDetailsPresenter: CountryDetailsPresenter)

}