package com.aubay.pokemon.home.model.remote

import com.aubay.pokemon.home.model.data.PokeInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FavoriteWebhook {

    @POST("af4c8b0c-bddd-4783-abce-1938087138c6")
    @FormUrlEncoded
    suspend fun favoritePokemon(@Field("pokemon") pokeInfo: PokeInfo,
                                @Field("favorited") favorited: Boolean)

}