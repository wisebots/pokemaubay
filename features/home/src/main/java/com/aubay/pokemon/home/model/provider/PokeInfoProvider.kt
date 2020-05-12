package com.aubay.pokemon.home.model.provider

import com.aubay.pokemon.core.domain.ResultDomain
import com.aubay.pokemon.core.repository.BaseRepository
import com.aubay.pokemon.core.repository.getData
import com.aubay.pokemon.home.model.data.PokeInfoListLocal
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.local.POKEMON_PROVIDER_ID
import com.aubay.pokemon.home.model.local.PokeDescriptionEntity
import com.aubay.pokemon.home.model.local.PokeListDAO
import com.aubay.pokemon.home.model.local.PokeListEntity
import com.aubay.pokemon.home.model.remote.FavoriteWebhook
import com.aubay.pokemon.home.model.remote.POKE_QUERY_LIMIT
import com.aubay.pokemon.home.model.remote.POKE_QUERY_OFFSET
import com.aubay.pokemon.home.model.remote.PokeListApi

interface PokeInfoProvider {
    suspend fun getListPokes(offset: Int, limit: Int): ResultDomain<PokeInfoListLocal>
    suspend fun getPagedListPokes(page: Int): List<PokeInfo>?
}

interface PokeDescriptionProvider {
    suspend fun getDescriptionPoke(pokedexId: Int): ResultDomain<PokeDescriptionLocal>
}

interface FavoriteProvider {
    suspend fun favoritePokemon(pokeInfo: PokeInfo, favorited: Boolean)
}

class PokeInfoProviderImpl(private val pokeListApi: PokeListApi,
                           private val pokeListDAO: PokeListDAO
) : BaseRepository<PokeInfoListLocal, PokeListEntity>(),
    PokeInfoProvider {

    override suspend fun getListPokes(offset: Int, limit: Int): ResultDomain<PokeInfoListLocal> {
        return fetchData(
            apiDataProvider = {
                pokeListApi.getListPokemon(offset, limit).getData(
                    fetchFromCacheAction = { pokeListDAO.getPokeForId(POKEMON_PROVIDER_ID) },
                    cacheAction = { pokeListDAO.savePokeList(it) })
            },
            dbDataProvider = { pokeListDAO.getPokeForId(POKEMON_PROVIDER_ID) }
        )
    }

    override suspend fun getPagedListPokes(page: Int): List<PokeInfo>? {
        return pokeListApi.getListPokemon(page*POKE_QUERY_OFFSET, POKE_QUERY_LIMIT).body()?.results
    }

}

class PokeDescriptionProviderImpl(private val pokeListApi: PokeListApi,
                                    private val pokeListDAO: PokeListDAO
) : BaseRepository<PokeDescriptionLocal, PokeDescriptionEntity>(),
        PokeDescriptionProvider {

    override suspend fun getDescriptionPoke(pokedexId: Int): ResultDomain<PokeDescriptionLocal> {
        return fetchData(
            apiDataProvider = {
                pokeListApi.getDescriptionPokemon(pokedexId).getData(
                    fetchFromCacheAction = { pokeListDAO.getPokeDescription(pokedexId) },
                    cacheAction = { pokeListDAO.savePokeDescription(it) })
            },
            dbDataProvider = { pokeListDAO.getPokeDescription(pokedexId) }
        )
    }
}

class FavoriteProviderImpl(private val favoriteWebhook: FavoriteWebhook
) : FavoriteProvider {

    override suspend fun favoritePokemon(pokeInfo: PokeInfo, favorited: Boolean) {
        favoriteWebhook.favoritePokemon(pokeInfo, favorited)
    }
}
