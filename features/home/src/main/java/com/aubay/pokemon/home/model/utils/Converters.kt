package com.aubay.pokemon.home.model.utils

import androidx.room.TypeConverter
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.remote.PokemonAbility
import com.aubay.pokemon.home.model.remote.PokemonMove
import com.aubay.pokemon.home.model.remote.PokemonType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPokeInfoListToJson(list: List<PokeInfo>?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToPokeInfoList(jsonList: String): List<PokeInfo> {
        val listPokes = object : TypeToken<List<PokeInfo>>() {}.type
        return gson.fromJson(jsonList, listPokes)
    }

    @TypeConverter
    fun fromMoveListToJson(list: List<PokemonMove>?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToMoveList(jsonList: String): List<PokemonMove> {
        val listMoves = object : TypeToken<List<PokemonMove>>() {}.type
        return gson.fromJson(jsonList, listMoves)
    }

    @TypeConverter
    fun fromAbilityListToJson(list: List<PokemonAbility>?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToAbilityList(jsonList: String): List<PokemonAbility> {
        val listAbilities = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.fromJson(jsonList, listAbilities)
    }

    @TypeConverter
    fun fromTypeListToJson(list: List<PokemonType>?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToTypeList(jsonList: String): List<PokemonType> {
        val listTypes = object : TypeToken<List<PokemonType>>() {}.type
        return gson.fromJson(jsonList, listTypes)
    }
}

