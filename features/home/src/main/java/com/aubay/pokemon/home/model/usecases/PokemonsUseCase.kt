package com.aubay.pokemon.home.model.usecases

import com.aubay.pokemon.core.domain.BaseUseCase
import com.aubay.pokemon.core.domain.PagedUseCase
import com.aubay.pokemon.core.domain.ResultDomain
import com.aubay.pokemon.core.domain.WebhookUseCase
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.provider.FavoriteProvider
import com.aubay.pokemon.home.model.provider.PokeDescriptionProvider
import com.aubay.pokemon.home.model.provider.PokeInfoProvider

interface ListPokemonsUseCase : PagedUseCase<Int, Int, PokeInfo> {
    override suspend operator fun invoke(page: Int): List<PokeInfo>?
}

class ListPokemonsUseCaseImpl(private val pokesInfoProvider: PokeInfoProvider) : ListPokemonsUseCase {
    override suspend operator fun invoke(page: Int)= pokesInfoProvider.getPagedListPokes(page)
}

interface GetPokemonDescriptionUseCase : BaseUseCase<Int, Int, PokeDescriptionLocal> {
    override suspend fun invoke(id: Int, text: Int): ResultDomain<PokeDescriptionLocal>
}

class GetPokemonDescriptionUseCaseImpl(private val pokeDescriptionProvider: PokeDescriptionProvider) : GetPokemonDescriptionUseCase {
    override suspend fun invoke(id: Int, pokedexId: Int): ResultDomain<PokeDescriptionLocal> = pokeDescriptionProvider.getDescriptionPoke(pokedexId)
}

interface FavoriteUseCase : WebhookUseCase<PokeInfo, Boolean> {
    override suspend fun invoke(key: Any, value: Any)
}

class FavoriteUseCaseImpl(private val favoriteProvider: FavoriteProvider) : FavoriteUseCase {
    override suspend fun invoke(key: Any, value: Any)= favoriteProvider.favoritePokemon(key as PokeInfo, value as Boolean)

}
