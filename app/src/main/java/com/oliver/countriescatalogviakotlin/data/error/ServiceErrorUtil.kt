package com.oliver.countriescatalogviakotlin.data.error

import com.oliver.countriescatalogviakotlin.mvp.base.MvpView
import timber.log.Timber

/**
 * Created by oliverabad on 25/7/18.
 */

class ServiceErrorUtil {

    companion object {
        fun showErrorDialog(getView: MvpView, error: ServiceError) {

            when (error) {
                ServiceError.NETWORK ->
                    //TODO: show network error dialog
                    Timber.e("no network connection")

                ServiceError.TIMEOUT ->
                    //TODO: show timeout error dialog
                    Timber.e("network timeout")

                ServiceError.TECHNICAL,
                ServiceError.FORBIDDEN,
                ServiceError.UNAUTHORIZED,
                ServiceError.REQUEST_NOT_FOUND,
                ServiceError.INTERNAL_SERVER_ERROR ->
                    //TODO: show server error dialog
                    Timber.e("server error")

                else ->
                    //TODO: show technical error dialog
                    Timber.e("technical error")
            }
        }
    }
}