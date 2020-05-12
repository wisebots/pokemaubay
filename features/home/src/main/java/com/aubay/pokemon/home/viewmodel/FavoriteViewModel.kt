package com.aubay.pokemon.home.viewmodel

import com.aubay.pokemon.core.base.BaseViewModel
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.usecases.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel<PokeInfo, PokeInfoFavoritedViewData>() {

    fun favorite(pokeInfo: PokeInfo, favorited: Boolean) = executeUseCase {
        favoriteUseCase(pokeInfo, favorited)
    }

}

sealed class PokeInfoFavoritedViewData