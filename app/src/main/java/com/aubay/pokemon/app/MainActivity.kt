package com.aubay.pokemon.app

import android.os.Bundle
import android.os.Handler
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import com.aubay.pokemon.R
import com.aubay.pokemon.core.base.BaseActivity
import com.aubay.pokemon.core.commons.TAG_FIRST_LAUNCH
import com.aubay.pokemon.core.commons.TIME_ALREADY_LAUNCH
import com.aubay.pokemon.core.commons.TIME_FIRST_LAUNCH
import com.aubay.pokemon.splash.navigation.FeatureNavigation
import org.jetbrains.anko.defaultSharedPreferences

class MainActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.apply {
            systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
            systemUiVisibility = SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        scheduleSplashScreen()
    }

    override fun navigate() {
        startActivity(FeatureNavigation.postHome("1", "2"))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                navigate()
                finish()
            },
            splashScreenDuration
        )
    }

    private fun getSplashScreenDuration(): Long {
        defaultSharedPreferences.let {
            return when(it.getBoolean(TAG_FIRST_LAUNCH, true)) {
                true -> {
                    it.edit().putBoolean(TAG_FIRST_LAUNCH, false).apply()
                    TIME_FIRST_LAUNCH
                }
                false -> TIME_ALREADY_LAUNCH
            }
        }
    }

    override fun getParameters(): Any {
        TODO("not implemented")
    }

    override fun subscribeBottomNavigation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}