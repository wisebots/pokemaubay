package com.aubay.pokemon.core.repository

import com.aubay.pokemon.core.commons.DB_ENTRY_ERROR
import com.aubay.pokemon.core.commons.GENERAL_NETWORK_ERROR
import com.aubay.pokemon.core.domain.FailureDomain
import com.aubay.pokemon.core.domain.HttpError
import com.aubay.pokemon.core.domain.ResultDomain
import com.aubay.pokemon.core.domain.SuccessDomain
import retrofit2.Response
import java.io.IOException


interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R): ResultDomain<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return SuccessDomain(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) SuccessDomain(cachedModel.mapToDomainModel()) else FailureDomain(HttpError(
                Throwable(DB_ENTRY_ERROR)
            ))
        }
        return FailureDomain(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return FailureDomain(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): ResultDomain<R> {
    try {
        onSuccess { return SuccessDomain(it.mapToDomainModel()) }
        onFailure { return FailureDomain(it) }
        return FailureDomain(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return FailureDomain(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}
