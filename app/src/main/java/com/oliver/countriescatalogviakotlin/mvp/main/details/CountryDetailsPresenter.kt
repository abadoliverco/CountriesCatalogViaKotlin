package com.oliver.countriescatalogviakotlin.mvp.main.details

import android.content.Context
import android.content.Intent
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import com.oliver.countriescatalogviakotlin.mvp.base.BasePresenter
import com.oliver.countriescatalogviakotlin.mvp.main.details.CountryDetailsActivity.Companion.BUNDLE_COUNTRY_MODEL

/**
 * Created by oliverabad on 28/7/18.
 */

class CountryDetailsPresenter(context: Context) : BasePresenter<CountryDetailsMvpView>(context) {

    private lateinit var countryModel: CountryModel

    override fun loadData() {
        getView()!!.showDetails(countryModel)
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun setBundle(intent: Intent) {
        val b = intent.extras
        if (b != null) {
            countryModel = intent.getParcelableExtra(BUNDLE_COUNTRY_MODEL) as CountryModel
        }
    }
}