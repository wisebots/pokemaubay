package com.aubay.pokemon.home.view.fragments

import android.annotation.SuppressLint
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aubay.pokemon.core.base.BaseFragment
import com.aubay.pokemon.home.R
import kotlinx.android.synthetic.main.activity_home.*

class AddProfileFragment : BaseFragment() {

    private val LINKEDIN_PROFILE = "https://www.linkedin.com/in/adrianobrito/"

    override fun getLayout() = R.layout.fragment_webview

    override fun viewReady() {

        subscribeWebview()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun subscribeWebview() {
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                val animate = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
                pbwLoadingProgress?.startAnimation(animate)
                super.onPageFinished(view, url);
                hideLoading(pbwLoadingProgress)

            }

        }
        showLoading(pbwLoadingProgress)
        webview.loadUrl(LINKEDIN_PROFILE)
    }

    companion object {
        fun newInstance()= AddProfileFragment()
    }
}
