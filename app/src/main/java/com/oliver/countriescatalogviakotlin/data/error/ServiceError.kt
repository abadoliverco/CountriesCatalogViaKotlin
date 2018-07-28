package com.oliver.countriescatalogviakotlin.data.error

import retrofit2.HttpException
import retrofit2.Retrofit
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by oliverabad on 24/7/18.
 */

enum class ServiceError {
    /* COMMON */
    TIMEOUT,
    NETWORK,
    UNAUTHORIZED, // 401
    FORBIDDEN, //403
    REQUEST_NOT_FOUND, // 404
    INTERNAL_SERVER_ERROR, // 500
    TECHNICAL,
    UNKNOWN;

    companion object {
        fun error(error: Throwable, retrofit: Retrofit): ServiceError {

            when (error) {
                is HttpException -> {
                    val response = error.response()

                    try {
                        val converter = retrofit.responseBodyConverter<ServiceError>(ServiceError::class.java, arrayOfNulls(0))
                        val serviceError = converter.convert(response.errorBody()!!)

                        if (serviceError != null) {
                            return serviceError
                        }

                        //Handling HTTP Code for cases when errorBody() is empty/null
                        when (error.code()) {
                            401 -> return UNAUTHORIZED
                            403 -> return FORBIDDEN
                            404 -> return REQUEST_NOT_FOUND
                            500 -> return INTERNAL_SERVER_ERROR
                        }

                    } catch (e: Exception) {
                        Timber.e(e.message)
                    }

                }
                is ConnectException -> return NETWORK
                is SocketTimeoutException -> return TIMEOUT
                is UnknownHostException -> return TECHNICAL
            }

            return UNKNOWN
        }
    }
}
