package com.aubay.pokemon.home.di

import androidx.room.Room
import com.aubay.pokemon.core.commons.POKES_TABLE_DESCRIPTIONS
import com.aubay.pokemon.core.commons.POKES_TABLE_NAME
import com.aubay.pokemon.core.repository.Connectivity
import com.aubay.pokemon.core.repository.ConnectivityImpl
import com.aubay.pokemon.home.model.local.PokeListDatabase
import com.aubay.pokemon.home.model.provider.*
import com.aubay.pokemon.home.model.remote.FavoriteWebhook
import com.aubay.pokemon.home.model.remote.PokeListApi
import com.aubay.pokemon.home.model.usecases.*
import com.aubay.pokemon.home.viewmodel.FavoriteViewModel
import com.aubay.pokemon.home.viewmodel.PokemonDescriptionViewModel
import com.aubay.pokemon.home.viewmodel.PokemonViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HomeModule {
    fun init() = loadKoinModules(interactorModule + presentationModule + repositoryModule + networkingModule + networkingWebhookModule + databaseModule)
}

val presentationModule = module(override = true) {
    viewModel {
        PokemonViewModel(
            get()
        )
    }
    viewModel {
        PokemonDescriptionViewModel (
            get()
        )
    }
    viewModel {
        FavoriteViewModel (
            get()
        )
    }
}

val interactorModule = module(override = true) {
    factory<ListPokemonsUseCase> {
        ListPokemonsUseCaseImpl(
            get()
        )
    }
    factory<GetPokemonDescriptionUseCase> {
        GetPokemonDescriptionUseCaseImpl(
            get()
        )
    }
    factory<FavoriteUseCase> {
        FavoriteUseCaseImpl(
            get()
        )
    }
}

val repositoryModule = module(override = true) {
    factory<PokeInfoProvider> { PokeInfoProviderImpl(get(), get()) }
    factory<PokeDescriptionProvider> { PokeDescriptionProviderImpl(get(), get()) }
    factory<FavoriteProvider> { FavoriteProviderImpl(get()) }
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
}

val databaseModule = module(override = true) {
    single {
        Room.databaseBuilder(androidContext(), PokeListDatabase::class.java, POKES_TABLE_NAME).fallbackToDestructiveMigration().build()
        Room.databaseBuilder(androidContext(), PokeListDatabase::class.java, POKES_TABLE_DESCRIPTIONS).fallbackToDestructiveMigration().build()
    }
    factory { get<PokeListDatabase>().pokeListDAO() }
}

private const val BASE_URL = "https://pokeapi.co/api/v2/"

val networkingModule = module(override = true) {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor }
    single {
        OkHttpClient.Builder().apply {
            callTimeout(10, TimeUnit.SECONDS)
        }.build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create(PokeListApi::class.java) }
    single { get<Retrofit>(qualifier=named("webhook")).create(FavoriteWebhook::class.java) }
}

private const val BASE_WEBHOOK = "https://webhook.site/"

val networkingWebhookModule = module(override = true) {
    single (qualifier=named("webhook")){
        Retrofit.Builder()
            .baseUrl(BASE_WEBHOOK)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
}