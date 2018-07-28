package com.oliver.countriescatalogviakotlin.data.api.wrapper

import com.google.gson.annotations.SerializedName

/**
 * Created by oliverabad on 21/7/18.
 */
open class ApiResponse(@SerializedName("IsSuccess") open val status: Boolean,
                       @SerializedName("UserMessage") open val message: String)