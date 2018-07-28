package com.oliver.countriescatalogviakotlin.mvp.injectable

import android.os.Bundle
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.di.component.ActivitiesComponent
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import com.oliver.countriescatalogviakotlin.di.component.DaggerActivitiesComponent
import com.oliver.countriescatalogviakotlin.di.module.PresenterModule
import com.oliver.countriescatalogviakotlin.mvp.base.BaseFragment

/**
 * Created by oliverabad on 27/7/18.
 */

abstract class InjectableFragment : BaseFragment() {

    private lateinit var activitiesComponent: ActivitiesComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectActivityComponent(getActivityComponent())
    }

    private fun getActivityComponent(): ActivitiesComponent {
        activitiesComponent = DaggerActivitiesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .presenterModule(PresenterModule(getApplicationComponent().getContext()))
                .build()
        return activitiesComponent
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return CountriesCatalogApplication.applicationComponent
    }

    protected abstract fun injectActivityComponent(activityComponent: ActivitiesComponent)
}
