package com.oliver.countriescatalogviakotlin.mvp.main.list

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.oliver.countriescatalogviakotlin.R
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import kotlinx.android.synthetic.main.item_country.view.*

/**
 * Created by oliverabad on 27/7/18.
 */

class CountryListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    val itemCard = view.findViewById(R.id.cardview_country) as CardView

    fun bindData(countryModel: CountryModel) {
        Glide.with(view.context!!).load(countryModel.flagPng).into(view.country_flag)
        view.country_name.text = countryModel.name
        view.country_region.text = countryModel.region
    }
}