package com.oliver.countriescatalogviakotlin.mvp.main.details

import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.mvp.base.MvpView

/**
 * Created by oliverabad on 28/7/18.
 */

interface CountryDetailsMvpView : MvpView {
    fun showDetails(countryModel: CountryModel)
}