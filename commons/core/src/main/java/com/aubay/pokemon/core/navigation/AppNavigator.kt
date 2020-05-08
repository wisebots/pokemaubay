package com.aubay.pokemon.core.navigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph

class AppNavigator(private val activity: AppCompatActivity) : Navigator {

    companion object {
        const val FEATURE = "feature"
    }

    override fun navigateTo(navController: NavController, navGraph: NavGraph){
        navController.navigate(navGraph.id)
    }

    override fun navigateTo(intent: Intent) = activity.startActivity(intent)

    private fun addDestinationGraph(navigationGraphRoute: NavigationGraphRoute,
                                    navController: NavController,
                                    context: Context
    ): NavGraph {

        // Find the resourceId of the graph we want to attach
        val navigationId = context.resources.getIdentifier(navigationGraphRoute.graphName, "navigation", navigationGraphRoute.packageName)

        // inflate the graph using the id obtained above
        val destinationGraph = navController.navInflater.inflate(navigationId)

        // Dynamically add the destination target to our primary graph
        navController.graph.addDestination(destinationGraph)

        return destinationGraph
    }
}
