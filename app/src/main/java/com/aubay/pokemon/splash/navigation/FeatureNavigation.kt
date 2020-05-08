package com.aubay.pokemon.splash.navigation

import android.content.Intent
import com.aubay.pokemon.core.navigation.DynamicFeature
import com.aubay.pokemon.core.navigation.loadIntentOrNull

val HOME = "com.aubay.pokemon.home.view.activities.HomeActivity"

object FeatureNavigation : DynamicFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    override val dynamicStart: Intent?
        get() = HOME.loadIntentOrNull()

    fun postHome(userId: String, postId: String): Intent? =
        HOME.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}
