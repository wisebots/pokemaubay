package com.aubay.pokemon.core.domain

interface BaseUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(id: T, text: K): ResultDomain<R>
}

interface PagedUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(page: T): List<R>?
}

interface WebhookUseCase<T : Any, K : Any> {
    suspend operator fun invoke(key: Any, value: Any)
}
