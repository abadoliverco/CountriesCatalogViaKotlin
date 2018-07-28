package com.oliver.countriescatalogviakotlin

import android.app.Application
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import com.oliver.countriescatalogviakotlin.di.component.DaggerApplicationComponent
import com.oliver.countriescatalogviakotlin.di.module.ApplicationModule
import timber.log.Timber

/**
 * Created by oliverabad on 21/7/18.
 */
class CountriesCatalogApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initializeInjector()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)
    }


    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
}