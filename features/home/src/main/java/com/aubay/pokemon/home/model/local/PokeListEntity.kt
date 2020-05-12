package com.aubay.pokemon.home.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aubay.pokemon.core.commons.POKES_TABLE_DESCRIPTIONS
import com.aubay.pokemon.core.commons.POKES_TABLE_NAME
import com.aubay.pokemon.core.repository.DomainMapper
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.data.PokeInfoListLocal
import com.aubay.pokemon.home.model.data.PokeDescriptionLocal
import com.aubay.pokemon.home.model.remote.PokemonAbility
import com.aubay.pokemon.home.model.remote.PokemonMove
import com.aubay.pokemon.home.model.remote.PokemonType
import com.aubay.pokemon.home.model.utils.Converters

const val POKEMON_PROVIDER_ID = 1

@Entity(tableName = POKES_TABLE_NAME)
data class PokeListEntity (@PrimaryKey val id: Int = POKEMON_PROVIDER_ID,
                            val count: Int = 0,
                            @TypeConverters(Converters::class) val pokes: List<PokeInfo>? ) :
    DomainMapper<PokeInfoListLocal> {

    override fun mapToDomainModel() = PokeInfoListLocal(count, pokes)

}

@Entity(tableName = POKES_TABLE_DESCRIPTIONS)
data class PokeDescriptionEntity(@PrimaryKey val id: Int = 0,
                                 val name: String = "",
                                 val height: Int = 0,
                                 val weight: Int = 0,
                                 val experience: Int = 0,
                                 @TypeConverters(Converters::class) val moves: List<PokemonMove>?,
                                 @TypeConverters(Converters::class) val abilities: List<PokemonAbility>?,
                                 @TypeConverters(Converters::class) val types: List<PokemonType>? ) :
    DomainMapper<PokeDescriptionLocal> {

    override fun mapToDomainModel(): PokeDescriptionLocal = PokeDescriptionLocal(id, name, height, weight, experience, moves, abilities, types)
}