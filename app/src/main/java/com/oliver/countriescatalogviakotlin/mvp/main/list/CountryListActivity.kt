package com.oliver.countriescatalogviakotlin.mvp.main.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.oliver.countriescatalogviakotlin.R
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.mvp.injectable.InjectableActivity
import com.oliver.countriescatalogviakotlin.mvp.main.details.CountryDetailsActivity
import kotlinx.android.synthetic.main.activity_country_list.*
import javax.inject.Inject

class CountryListActivity : InjectableActivity(), CountryListMvpView {


    @Inject
    lateinit var presenter: CountryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()!!.inject(this)
        presenter.attachView(this)
        setupRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_country_list
    }

    override fun hasBackButton(): Boolean {
        return false
    }

    override fun setHeaderTitle(): String {
        return getString(R.string.title_country_list)
    }

    private fun setupRecyclerView() {
        with(countries_list) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun showListOfCountries(list: MutableList<CountryModel>) {
        var countryAdapter = CountryListAdapter(list)
        countries_list.adapter = countryAdapter
        presenter.onSelectedCountry(countryAdapter)
    }

    override fun showCountryDetailsScreen(countryModel: CountryModel) {
        CountryDetailsActivity.start(this, countryModel)
    }
}
