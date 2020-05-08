package com.aubay.pokemon.core.navigation

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface Navigator {

    fun navigateTo(navController: NavController, navGraph: NavGraph)

    fun navigateTo(intent: Intent)

}