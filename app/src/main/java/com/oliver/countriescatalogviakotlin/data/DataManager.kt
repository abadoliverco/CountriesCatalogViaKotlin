package com.oliver.countriescatalogviakotlin.data

import android.content.Context
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.data.api.MainControllerApi
import com.oliver.countriescatalogviakotlin.data.api.wrapper.ListWrapper
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.di.component.DaggerDataManagerComponent
import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject

/**
 * Created by oliverabad on 21/7/18.
 */
class DataManager(var context: Context) {

    @Inject lateinit var mainControllerApi: MainControllerApi

    @Inject
    lateinit var retrofit: Retrofit

    init {
        this.context = context
        injectDependencies()
    }

    private fun injectDependencies() {
        DaggerDataManagerComponent.builder()
                .applicationComponent(CountriesCatalogApplication.applicationComponent)
                .build()
                .inject(this)
    }

    internal fun getRetrofit(): Retrofit {
        return retrofit
    }

    fun getCountriesList(): Observable<ListWrapper<CountryModel>> {
        return mainControllerApi.getCountriesList()
    }
}