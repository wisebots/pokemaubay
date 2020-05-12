package com.aubay.pokemon.home.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aubay.pokemon.home.model.utils.Converters

@Database(entities = [PokeListEntity::class, PokeDescriptionEntity::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokeListDatabase : RoomDatabase() {

    abstract fun pokeListDAO(): PokeListDAO

}