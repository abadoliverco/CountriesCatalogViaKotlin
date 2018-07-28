package com.oliver.countriescatalogviakotlin.mvp.main.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.oliver.countriescatalogviakotlin.R
import com.oliver.countriescatalogviakotlin.data.model.CountryModel
import rx.Observable
import rx.subjects.PublishSubject

/**
 * Created by oliverabad on 27/7/18.
 */

class CountryListAdapter(private val countryList: MutableList<CountryModel>) : RecyclerView.Adapter<CountryListViewHolder>() {

    private val onSelectedCountry = PublishSubject.create<CountryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        return CountryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        (holder as CountryListViewHolder).bindData(countryList[position])
        (holder as CountryListViewHolder).itemCard.setOnClickListener { onSelectedCountry.onNext(countryList[position]) }
    }

    fun getSelectedCountry(): Observable<CountryModel> {
        return onSelectedCountry.asObservable()
    }
}