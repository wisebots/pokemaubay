package com.aubay.pokemon.di

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.aubay.pokemon.core.extensions.CoroutineContextProvider
import com.aubay.pokemon.core.navigation.AppFragmentNavigator
import com.aubay.pokemon.core.navigation.AppNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { CoroutineContextProvider() }
    single<SharedPreferences> { androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE) }
    single { (activity: AppCompatActivity) ->
        AppNavigator(
            activity
        )
    }
    single { (activity: FragmentActivity, toFragment: Fragment) ->
        AppFragmentNavigator(
            activity,
            toFragment
        )
    }
}