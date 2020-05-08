package com.aubay.pokemon.core.navigation

import android.content.Intent
import android.util.Log
import com.aubay.pokemon.core.commons.PACKAGE_DEFAULT
import com.aubay.pokemon.core.commons.TAG
import java.lang.NullPointerException

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_DEFAULT, className)

fun String.loadIntentOrNull(): Intent? =
    try {
        Log.i(TAG, "IntentLoader ${this@loadIntentOrNull}")
        Class.forName(this).run {
            intentTo(
                this@loadIntentOrNull
            )
        }
    } catch (e: ClassNotFoundException) {
        Log.e(TAG,"IntentLoader error classpath not found ${e.message}")
        null
    } catch (e: NullPointerException) {
        Log.e(TAG,"IntentLoader error classpath nullpointer ${e.message}")
        null
    }
