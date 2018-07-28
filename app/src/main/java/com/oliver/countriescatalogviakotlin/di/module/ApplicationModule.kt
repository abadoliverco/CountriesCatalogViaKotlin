package com.oliver.countriescatalogviakotlin.di.module

import android.app.Application
import android.content.Context
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.data.DataManager
import com.oliver.countriescatalogviakotlin.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by oliverabad on 21/7/18.
 */

@Module
class ApplicationModule(private val baseApp: CountriesCatalogApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication() : Application {
        return baseApp
    }

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return baseApp.getApplicationContext()
    }


    @Provides
    @Singleton
    internal fun providesDataManager(context: Context): DataManager {
        return DataManager(context)
    }


}