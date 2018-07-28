package com.oliver.countriescatalogviakotlin.di.component

import com.oliver.countriescatalogviakotlin.data.DataManager
import com.oliver.countriescatalogviakotlin.di.module.ApiServiceModule
import com.oliver.countriescatalogviakotlin.di.module.NetworkModule
import com.oliver.countriescatalogviakotlin.di.scope.PerDataManager
import dagger.Component

/**
 * Created by oliverabad on 21/7/18.
 */

@PerDataManager
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ApiServiceModule::class), (NetworkModule::class)])
interface DataManagerComponent{
    fun inject(dataManager: DataManager)
}