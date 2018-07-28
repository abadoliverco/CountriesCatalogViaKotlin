package com.oliver.countriescatalogviakotlin.mvp.base

import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent

/**
 * Created by oliverabad on 23/7/18.
 */

interface Presenter<V : MvpView> {

    fun attachView(mvpView : V)

    fun detachView()

    fun loadData()

    fun injectComponent(applicationComponent: ApplicationComponent)
}