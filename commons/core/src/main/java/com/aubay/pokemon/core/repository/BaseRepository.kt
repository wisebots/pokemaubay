package com.aubay.pokemon.core.repository

import com.aubay.pokemon.core.commons.DB_ENTRY_ERROR
import com.aubay.pokemon.core.commons.GENERAL_NETWORK_ERROR
import com.aubay.pokemon.core.domain.FailureDomain
import com.aubay.pokemon.core.domain.HttpError
import com.aubay.pokemon.core.domain.ResultDomain
import com.aubay.pokemon.core.domain.SuccessDomain
import com.aubay.pokemon.core.extensions.CoroutineContextProvider
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {

    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> ResultDomain<T>,
        dbDataProvider: suspend () -> R
    ): ResultDomain<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) SuccessDomain(dbResult.mapToDomainModel()) else FailureDomain(
                    HttpError(Throwable(DB_ENTRY_ERROR))
                )
            }
        }
    }

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: () -> ResultDomain<T>): ResultDomain<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            FailureDomain(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }
}