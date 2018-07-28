package com.oliver.countriescatalogviakotlin.di.component

import com.oliver.countriescatalogviakotlin.di.module.PresenterModule
import com.oliver.countriescatalogviakotlin.di.scope.PerActivity
import com.oliver.countriescatalogviakotlin.mvp.base.BaseActivity
import com.oliver.countriescatalogviakotlin.mvp.main.details.CountryDetailsActivity
import com.oliver.countriescatalogviakotlin.mvp.main.list.CountryListActivity
import dagger.Component

/**
 * Created by oliverabad on 25/7/18.
 */

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(PresenterModule::class)])
interface ActivitiesComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(countryListActivity: CountryListActivity)
    fun inject(countryDetailsActivity: CountryDetailsActivity)
}