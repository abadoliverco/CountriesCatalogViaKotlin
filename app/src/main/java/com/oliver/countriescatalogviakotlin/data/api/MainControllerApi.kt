package com.oliver.countriescatalogviakotlin.data.api

import com.oliver.countriescatalogviakotlin.data.api.wrapper.ListWrapper
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import retrofit2.http.GET
import rx.Observable

/**
 * Created by oliverabad on 21/7/18.
 */

interface MainControllerApi {

    @GET("getCountries")
    fun getCountriesList(): Observable<ListWrapper<CountryModel>>

}