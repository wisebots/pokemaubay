package com.aubay.pokemon.home.di

import com.aubay.pokemon.home.model.usecases.ListPokemonsUseCase
import com.aubay.pokemon.home.model.usecases.ListPokemonsUseCaseImpl
import org.koin.dsl.module

val interactorModuleTest = module {
    factory<ListPokemonsUseCase> {
        ListPokemonsUseCaseImpl(
            get()
        )
    }
}