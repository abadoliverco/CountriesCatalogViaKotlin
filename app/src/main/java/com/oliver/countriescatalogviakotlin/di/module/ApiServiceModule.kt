package com.oliver.countriescatalogviakotlin.di.module

import com.oliver.countriescatalogviakotlin.data.api.MainControllerApi
import com.oliver.countriescatalogviakotlin.di.scope.PerDataManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by oliverabad on 21/7/18.
 */

@Module
class ApiServiceModule {

    @Provides
    @PerDataManager
    internal fun provideMainControllerApi(retrofit: Retrofit): MainControllerApi {
        return retrofit.create<MainControllerApi>(MainControllerApi::class.java)
    }
}