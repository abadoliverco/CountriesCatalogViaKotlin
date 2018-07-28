package com.oliver.countriescatalogviakotlin.mvp.main.list

import android.content.Context
import com.oliver.countriescatalogviakotlin.data.DataManager
import com.oliver.countriescatalogviakotlin.data.api.wrapper.ListWrapper
import com.oliver.countriescatalogviakotlin.data.error.ServiceError
import com.oliver.countriescatalogviakotlin.data.error.ServiceErrorUtil
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import com.oliver.countriescatalogviakotlin.mvp.base.BasePresenter
import rx.Observer
import javax.inject.Inject

/**
 * Created by oliverabad on 27/7/18.
 */

class CountryListPresenter(context: Context) : BasePresenter<CountryListMvpView>(context) {

    @Inject
    lateinit var dataManager: DataManager

    override fun loadData() {
        getView()!!.showLoader()

        this.baseSubscribe(dataManager.getCountriesList(), object : BaseSubscriber<ListWrapper<CountryModel>> {

            override fun onSuccess(countriesModelListWrapper: ListWrapper<CountryModel>) {
                getView()!!.showListOfCountries(countriesModelListWrapper.data)
            }

            override fun onError(error: ServiceError) {
                ServiceErrorUtil.showErrorDialog(getView()!!, error)
            }

            override fun onComplete() {
                getView()!!.hideLoader()
            }
        })
    }

    override fun injectComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun onSelectedCountry(adapter: CountryListAdapter) {
        adapter.getSelectedCountry().subscribe(object : Observer<CountryModel> {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(countryModel: CountryModel) {
                getView()!!.showCountryDetailsScreen(countryModel)
            }
        })
    }

}