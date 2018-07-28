package com.oliver.countriescatalogviakotlin.data.api.wrapper

import com.google.gson.annotations.SerializedName

/**
 * Created by oliverabad on 21/7/18.
 */

data class ListWrapper<T> (@SerializedName("Response") val data: MutableList<T>,
                           override val status: Boolean,
                           override val message: String) : ApiResponse(status, message)