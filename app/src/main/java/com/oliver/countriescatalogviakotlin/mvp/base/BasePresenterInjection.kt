package com.oliver.countriescatalogviakotlin.mvp.base

import com.oliver.countriescatalogviakotlin.data.DataManager
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by oliverabad on 23/7/18.
 */

class BasePresenterInjection {

    @Inject lateinit var dataManager: DataManager

    fun getRetrofit() : Retrofit {
        return dataManager.getRetrofit()
    }

    internal fun getDataManager() : DataManager {
        return dataManager
    }
}