package com.oliver.countriescatalogviakotlin.mvp.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oliver.countriescatalogviakotlin.R
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by oliverabad on 23/7/18.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {


    private var onStartCount = 0

    private var loadingDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        loadingDialog = ProgressDialog(this)
        loadingDialog!!.isIndeterminate
        loadingDialog!!.setMessage("Loading...")

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.title = setHeaderTitle()

            if (hasBackButton()) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
                toolbar!!.setNavigationOnClickListener { onBackPressed() }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (onStartCount > 1) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        } else if (onStartCount == 1) {
            onStartCount++
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        hideLoader()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun showLoader() {
        loadingDialog!!.show()

    }

    override fun hideLoader() {
        if (loadingDialog!!.isShowing) loadingDialog!!.dismiss()
    }


    abstract fun getLayoutResource(): Int
    abstract fun hasBackButton(): Boolean
    abstract fun setHeaderTitle(): String

    fun getToolbar() {
        toolbar!!
    }


}