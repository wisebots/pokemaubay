package com.aubay.pokemon.home.navigation

import android.content.Intent
import com.aubay.pokemon.core.navigation.DynamicFeature
import com.aubay.pokemon.core.navigation.loadIntentOrNull

// for feature navigation if app had another features
object FeatureNavigation : DynamicFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    val DETAILS = "com.aubay.pokemon.details.view.activities"

    override val dynamicStart: Intent?
        get() = DETAILS.loadIntentOrNull()

    fun postDetails(): Intent? =
        DETAILS.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY,"")
                putExtra(POST_ID_KEY,"")
            }
}
