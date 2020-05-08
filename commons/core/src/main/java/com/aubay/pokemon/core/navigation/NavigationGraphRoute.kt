package com.aubay.pokemon.core.navigation

import androidx.navigation.NavGraph

interface NavigationGraphRoute {

    var navGraph: NavGraph

    val graphName: String

    val packageName: String
}