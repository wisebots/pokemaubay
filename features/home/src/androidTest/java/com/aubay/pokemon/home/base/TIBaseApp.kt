package com.aubay.pokemon.home.base

import com.aubay.pokemon.app.App
import org.koin.core.module.Module

class TIBaseApp : App() {
    override fun provideComponent() = listOf<Module>()
}