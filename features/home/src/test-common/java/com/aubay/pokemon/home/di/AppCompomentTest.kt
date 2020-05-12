package com.aubay.pokemon.home.di

fun configureAppComponent(baseApi: String) = listOf(
    configureNetworkModuleForTest(baseApi),
    presentationModule,
    repositoryModule
)