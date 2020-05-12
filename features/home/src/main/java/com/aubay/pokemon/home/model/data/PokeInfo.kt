package com.aubay.pokemon.home.model.data

import android.os.Parcelable
import com.aubay.pokemon.home.model.remote.PokemonAbility
import com.aubay.pokemon.home.model.remote.PokemonMove
import com.aubay.pokemon.home.model.remote.PokemonType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokeInfo(val id: Int, val name: String, val url: String, var count: Int) : Parcelable

data class PokeInfoListLocal(val count: Int,
                             val pokes: List<PokeInfo>? = null)

data class PokeDescriptionLocal(val id: Int,
                                val name: String,
                                val height: Int,
                                val weight: Int,
                                val experience: Int,
                                val moves: List<PokemonMove>? = null,
                                val abilities: List<PokemonAbility>? = null,
                                val types: List<PokemonType>? = null )