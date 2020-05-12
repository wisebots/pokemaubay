package com.aubay.pokemon.home.repository

import com.aubay.pokemon.home.base.BaseUnitTest
import com.aubay.pokemon.home.di.configureAppComponent
import com.aubay.pokemon.home.model.provider.PokeInfoProvider
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import retrofit2.HttpException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class UserRepositoryTest: BaseUnitTest() {

    private val listPokemonProvider by inject<PokeInfoProvider>()

    override fun isMockServerEnabled() = true

    override fun setUp() {
        super.setUp()
        loadKoinModules(configureAppComponent(getMockUrl()))
    }

    // TESTS

    @Test
    fun `search pokemons by name and succeed`() {
        mockHttpResponse("list_pokemon.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val pokes = listPokemonProvider.getPagedListPokes(1)
            assertEquals(1, pokes?.size)
            assertEquals("bulbasaur", pokes?.first()?.name)
            assertEquals("https://pokeapi.co/api/v2/pokemon/1/", pokes?.first()?.url)
        }
    }

    @Test(expected = HttpException::class)
    fun `search users by name and fail`() {
        mockHttpResponse("list_pokemon.json", HttpURLConnection.HTTP_FORBIDDEN)
        runBlocking {
            listPokemonProvider.getPagedListPokes(-1)
        }
    }
}