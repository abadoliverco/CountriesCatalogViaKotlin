package com.oliver.countriescatalogviakotlin.mvp.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by oliverabad on 23/7/18.
 */

abstract class BaseFragment : Fragment(), MvpView {

    private var loadingDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = ProgressDialog(activity)
        loadingDialog!!.isIndeterminate
        loadingDialog!!.setMessage("Loading...")
    }

    override fun showLoader() {
        loadingDialog!!.show()
    }

    override fun hideLoader() {
        if (loadingDialog!!.isShowing) loadingDialog!!.dismiss()
    }
}