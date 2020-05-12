package com.aubay.pokemon.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.aubay.pokemon.core.extensions.gone
import com.aubay.pokemon.core.extensions.logDebug
import com.aubay.pokemon.core.extensions.visible
import com.aubay.pokemon.core.navigation.AppFragmentNavigator
import com.aubay.pokemon.core.navigation.AppNavigator
import com.aubay.pokemon.core.widgets.ProgressBarWidget
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {

    protected val appNavigator: AppNavigator by inject { parametersOf(this) }

    protected val appFragmentNavigator: AppFragmentNavigator by inject { parametersOf(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedElementEnterTransition = TransitionInflater
            .from(context).inflateTransition(
                android.R.transition.move
            )
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onCreate fragment")
        viewReady()
    }

    fun onBackPressed() = (activity as BaseActivity).onBackPressed()

    fun onNextPressed() = (activity as BaseActivity).onNextPressed()

    fun onBeforePressed() = (activity as BaseActivity).onBeforePressed()

    abstract fun viewReady()

    abstract fun getLayout(): Int

    fun log(message: String){
        logDebug("${this::class.java.canonicalName}", message)
    }

    open fun getParameters(): Any {
        return (activity as BaseActivity).getParameters()
    }

    open fun showError(@StringRes errorMessage: Int, rootView: View) {
        (activity as BaseActivity).showError(errorMessage, rootView)
    }

    open fun showError(errorMessage: String?, rootView: View) {
        (activity as BaseActivity).showError(errorMessage, rootView)
    }

    fun showLoading(progressBar: ProgressBarWidget?) {
        progressBar?.visible()
    }

    fun hideLoading(progressBar: ProgressBarWidget?) {
        progressBar?.gone()
    }

    open fun showLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).showLoading(progressBar)
    }

    open fun hideLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).hideLoading(progressBar)
    }

    open fun navigate() {
        log("navigate to feature")
        (activity as BaseActivity).navigate()
    }

    open fun finishFeature() {
        log("finish feature")
        (activity as BaseActivity).finish()
    }

    open fun subscribeBottomNavigation() {
        (activity as BaseActivity).subscribeBottomNavigation()
    }
}