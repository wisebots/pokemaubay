package com.aubay.pokemon.home.di

import com.aubay.pokemon.home.model.remote.PokeListApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configureNetworkModuleForTest(baseApi: String) = module {

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory{ get<Retrofit>().create(PokeListApi::class.java) }
}