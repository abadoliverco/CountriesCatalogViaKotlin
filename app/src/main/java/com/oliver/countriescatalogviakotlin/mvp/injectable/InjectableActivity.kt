package com.oliver.countriescatalogviakotlin.mvp.injectable

import android.os.Bundle
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.di.component.ActivitiesComponent
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import com.oliver.countriescatalogviakotlin.di.component.DaggerActivitiesComponent
import com.oliver.countriescatalogviakotlin.di.module.PresenterModule
import com.oliver.countriescatalogviakotlin.mvp.base.BaseActivity

/**
 * Created by oliverabad on 25/7/18.
 */

abstract class InjectableActivity: BaseActivity() {

    private var activitiesComponent: ActivitiesComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getApplicationComponent().inject(this)

    }

    protected fun getActivityComponent(): ActivitiesComponent? {
        if (activitiesComponent == null) {
            activitiesComponent = DaggerActivitiesComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .presenterModule(PresenterModule(this))
                    .build()
        }
        return activitiesComponent
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return CountriesCatalogApplication.applicationComponent
    }
}