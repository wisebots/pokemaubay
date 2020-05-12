package com.aubay.pokemon.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.aubay.pokemon.core.commons.FEATURE_NIGHT
import com.aubay.pokemon.core.extensions.onlyDebug
import com.aubay.pokemon.di.appComponent
import com.aubay.pokemon.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            if(onlyDebug()) androidLogger(Level.DEBUG)
            modules(appModule)
        }

        // Plus feature
        setFeatureNightMode()
    }

    private fun setFeatureNightMode() {
        val nightMode = when (FEATURE_NIGHT) {
            true -> AppCompatDelegate.MODE_NIGHT_YES
            false -> AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    open fun provideComponent() = appComponent
}