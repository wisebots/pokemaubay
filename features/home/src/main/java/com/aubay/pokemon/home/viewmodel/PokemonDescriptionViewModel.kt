package com.aubay.pokemon.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.aubay.pokemon.core.base.BaseViewModel
import com.aubay.pokemon.core.base.Error
import com.aubay.pokemon.core.base.Success
import com.aubay.pokemon.core.domain.onFailure
import com.aubay.pokemon.core.domain.onSuccess
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.usecases.GetPokemonDescriptionUseCase

class PokemonDescriptionViewModel(private val getDescription: GetPokemonDescriptionUseCase
) : BaseViewModel<PokeDescriptionLocal, PokeDescriptionViewData>() {

    val pokeDescription = MutableLiveData<PokeDescriptionLocal>()

    fun showDescription(pokedexId: Int) = executeUseCase {
        getDescription(pokedexId, pokedexId)
            .onSuccess { _viewState.value = Success(it) }
            .onFailure { _viewState.value = Error(it.throwable) }
    }

}

sealed class PokeDescriptionViewData