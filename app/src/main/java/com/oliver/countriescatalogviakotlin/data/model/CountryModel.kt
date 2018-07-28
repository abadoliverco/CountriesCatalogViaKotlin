package com.oliver.countriescatalogviakotlin.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by oliverabad on 21/7/18.
 */

@Parcelize
data class CountryModel(
        @SerializedName("Name") val name: String,
        @SerializedName("NativeName") val nativeName: String,
        @SerializedName("Region") val region: String,
        @SerializedName("SubRegion") val subRegion: String,
        @SerializedName("NativeLanguage") val nativeLanguage: String,
        @SerializedName("Latitude") val latitude: String,
        @SerializedName("Longitude") val longitude: String,
        @SerializedName("CurrencyCode") val currencyCode: String,
        @SerializedName("CurrencyName") val currencyName: String,
        @SerializedName("CurrencySymbol") val currencySymbol: String,
        @SerializedName("FlagPng") val flagPng: String) : Parcelable