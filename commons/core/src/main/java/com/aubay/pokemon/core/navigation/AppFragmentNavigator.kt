package com.aubay.pokemon.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.aubay.pokemon.core.R
import com.aubay.pokemon.core.extensions.showFragment

class AppFragmentNavigator(private val activity: FragmentActivity, private val toFragment: Fragment) :
    FragmentNavigator {

    override fun showFragmentContainer() = activity.showFragment(toFragment, R.id.fragmentContainer, true)

}
