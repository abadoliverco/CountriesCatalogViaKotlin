package com.oliver.countriescatalogviakotlin.data.api.wrapper

import com.google.gson.annotations.SerializedName

/**
 * Created by oliverabad on 21/7/18.
 */

class ObjectWrapper<T> (
        @SerializedName("data") val data: T,
        override val status: Boolean,
        override val message: String) : ApiResponse(status, message)