package com.aubay.pokemon.core.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.aubay.pokemon.core.commons.EMPTY_STRING
import com.aubay.pokemon.core.extensions.*
import com.aubay.pokemon.core.widgets.ProgressBarWidget

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        log("onCreate activity")
    }

    abstract fun getLayout(): Int

    abstract fun navigate()

    abstract fun getParameters() : Any

    abstract fun subscribeBottomNavigation()

    fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) = snackbar(errorMessage ?: EMPTY_STRING, rootView)

    fun showLoading(progressBar: ProgressBar?) = progressBar?.visible()

    fun hideLoading(progressBar: ProgressBar?) = progressBar?.gone()

    fun showLoading(progressBar: ProgressBarWidget?)= progressBar?.visible()

    fun hideLoading(progressBar: ProgressBarWidget?)= progressBar?.gone()

    fun addFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
        showFragment(fragment, containerId, addToBackStack)
    }

    fun addFragment(fragment: Fragment, containerId: Int, transitionView: ImageView, addToBackStack: Boolean = false) {
        showFragment(fragment, containerId, transitionView, addToBackStack)
    }

    fun log(message: String){
        logDebug("${this::class.java.canonicalName}", message)
    }

    override fun onBackPressed() {
        log("backstack ${supportFragmentManager.backStackEntryCount}")
        if (supportFragmentManager.backStackEntryCount < 1) finish() else goBack()
    }
}