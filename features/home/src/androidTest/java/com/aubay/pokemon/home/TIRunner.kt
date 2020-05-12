package com.aubay.pokemon.home

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.aubay.pokemon.home.base.TIBaseApp

/**
 * Custom runner to disable dependency injection.
 */
class TIRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TIBaseApp::class.java.name, context)
    }
}