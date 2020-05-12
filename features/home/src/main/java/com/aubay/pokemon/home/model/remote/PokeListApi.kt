package com.aubay.pokemon.home.model.remote

import com.aubay.pokemon.core.repository.RoomMapper
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.local.POKEMON_PROVIDER_ID
import com.aubay.pokemon.home.model.local.PokeDescriptionEntity
import com.aubay.pokemon.home.model.local.PokeListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val POKE_QUERY_LIMIT = 100
const val POKE_QUERY_OFFSET = 100

interface PokeListApi {

    @GET("pokemon")
    suspend fun getListPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokeListResponse>

    @GET("pokemon/{pokedexId}")
    suspend fun getDescriptionPokemon(@Path("pokedexId") pokedexId: Int): Response<PokeDescriptionResponse>
}

data class PokeListResponse ( val count: Int = 0,
                              val next: String = "",
                              val previous: String = "",
                              val results: List<PokeInfo> ) : RoomMapper<PokeListEntity> {

    override fun mapToRoomEntity()= PokeListEntity(POKEMON_PROVIDER_ID, count, results)
}

data class PokeDescriptionResponse ( val id: Int,
                                     val name: String,
                                     val height: Int,
                                     val weight: Int,
                                     val base_experience: Int,
                                     val moves: List<PokemonMove>,
                                     val abilities: List<PokemonAbility>,
                                     val types: List<PokemonType> ) : RoomMapper<PokeDescriptionEntity> {

    override fun mapToRoomEntity() = PokeDescriptionEntity(id, name, height, weight, base_experience, moves, abilities, types)
}

data class PokemonMove ( val move: PokemonMoveDetail? )

data class PokemonMoveDetail ( val name: String?,
                               val url: String? )

data class PokemonAbility ( val ability: PokemonAbilityDetail? )

data class PokemonAbilityDetail ( val name: String?,
                                  val url: String? )

data class PokemonType ( val slot: Int,
                         val type: PokemonTypeDetail? )

data class PokemonTypeDetail ( val name: String?,
                               val url: String? )