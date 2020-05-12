package com.aubay.pokemon.home.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.aubay.pokemon.core.base.BaseViewModel
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.provider.PokeDataSource
import com.aubay.pokemon.home.model.remote.POKE_QUERY_OFFSET
import com.aubay.pokemon.home.model.usecases.ListPokemonsUseCase

class PokemonViewModel(private val listPokes: ListPokemonsUseCase
            ) : BaseViewModel<PokeInfo, PokesViewData>() {

    private val pokeDataSource = PokeDataSource.Factory(listPokes, viewModelScope)

    val pokes = LivePagedListBuilder(pokeDataSource, POKE_QUERY_OFFSET).build()

    val getPokesState = Transformations.switchMap(pokeDataSource.sourceLiveData) {
        it.getPokesState
    }

}

sealed class PokesViewData