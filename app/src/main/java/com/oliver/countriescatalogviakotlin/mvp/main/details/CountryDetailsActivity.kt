package com.oliver.countriescatalogviakotlin.mvp.main.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.oliver.countriescatalogviakotlin.R
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import com.oliver.countriescatalogviakotlin.mvp.injectable.InjectableActivity
import kotlinx.android.synthetic.main.activity_country_details.*
import javax.inject.Inject

/**
 * Created by oliverabad on 28/7/18.
 */

class CountryDetailsActivity : InjectableActivity(), CountryDetailsMvpView {

    @Inject
    lateinit var presenter: CountryDetailsPresenter

    companion object {
        const val BUNDLE_COUNTRY_MODEL: String = "BUNDLE_COUNTRY_MODEL"

        fun start(context: Context, countryModel: CountryModel) {
            var intent = Intent(context, CountryDetailsActivity::class.java)
            with(intent) {
                putExtra(BUNDLE_COUNTRY_MODEL, countryModel)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()!!.inject(this)
        presenter.setBundle(intent)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_country_details
    }

    override fun hasBackButton(): Boolean {
        return true
    }

    override fun setHeaderTitle(): String {
        return getString(R.string.title_country_details)
    }

    override fun showDetails(countryModel: CountryModel) {
        Glide.with(this).load(countryModel.flagPng).into(country_flag)
        country_name.text = countryModel.name
        currency_code_value.text = countryModel.currencyCode
        currency_name_value.text = countryModel.currencyName
        latitude_value.text = countryModel.latitude
        longitude_value.text = countryModel.longitude
        region_value.text = countryModel.region
        sub_region_value.text = countryModel.subRegion
        native_language_value.text = countryModel.nativeLanguage
        native_name_value.text = countryModel.nativeName
    }
}