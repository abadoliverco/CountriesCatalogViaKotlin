package com.oliver.countriescatalogviakotlin.mvp.main.list

import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.mvp.base.MvpView

/**
 * Created by oliverabad on 27/7/18.
 */

interface CountryListMvpView : MvpView {

    fun showListOfCountries(list: MutableList<CountryModel>)
    fun showCountryDetailsScreen(countryModel: CountryModel)
}