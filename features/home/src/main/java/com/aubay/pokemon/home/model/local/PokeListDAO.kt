package com.aubay.pokemon.home.model.local

import androidx.room.*
import com.aubay.pokemon.core.commons.POKES_TABLE_DESCRIPTIONS
import com.aubay.pokemon.core.commons.POKES_TABLE_NAME

@Dao
interface PokeListDAO {

    @Transaction
    suspend fun updatePokesAndReturn(pokeList: PokeListEntity): PokeListEntity {
        savePokeList(pokeList)
        return getPokeForId(pokeList.id ?: 0)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokeList(pokeList: PokeListEntity)

    @Query("SELECT * FROM $POKES_TABLE_NAME WHERE ID = :id LIMIT 1")
    suspend fun getPokeForId(id: Int): PokeListEntity


    @Transaction
    suspend fun updatePokeDescriptionAndReturn(pokeDescription: PokeDescriptionEntity): PokeDescriptionEntity {
        savePokeDescription(pokeDescription)
        return getPokeDescription(pokeDescription.id ?: 0)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokeDescription(pokeDescription: PokeDescriptionEntity)

    @Query("SELECT * FROM $POKES_TABLE_DESCRIPTIONS WHERE ID = :pokedexId LIMIT 1")
    suspend fun getPokeDescription(pokedexId: Int): PokeDescriptionEntity
}