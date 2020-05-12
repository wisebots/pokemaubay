package com.aubay.pokemon.home.model.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.aubay.pokemon.home.model.data.PokeInfo
import com.aubay.pokemon.home.model.usecases.ListPokemonsUseCase
import kotlinx.coroutines.*

class PokeDataSource(
    private val listPokes: ListPokemonsUseCase,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, PokeInfo>() {

    class Factory(
        private val listPokes: ListPokemonsUseCase,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, PokeInfo>() {
        val sourceLiveData = MutableLiveData<PokeDataSource>()
        private lateinit var latestSource: PokeDataSource

        override fun create(): DataSource<Int, PokeInfo> {
            latestSource = PokeDataSource(listPokes, scope)
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }

    private val _getPokesState = MutableLiveData<UiState>()
    val getPokesState: LiveData<UiState> = _getPokesState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokeInfo>
    ) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getPokesState.postValue(UiState.Error(throwable))
        }) {
            _getPokesState.postValue(UiState.Loading)
            val pokes = listPokes(0)
            callback.onResult(pokes!!, null, 2)
            _getPokesState.postValue(UiState.Complete)
            if (pokes.isEmpty()) {
                _getPokesState.postValue(UiState.Empty)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokeInfo>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getPokesState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(listPokes(params.key)!!, params.key + 1)
            _getPokesState.postValue(UiState.Complete)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokeInfo>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getPokesState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(listPokes(params.key)!!, params.key - 1)
            _getPokesState.postValue(UiState.Complete)
        }
    }

}

sealed class UiState {
    object Complete : UiState()
    object Loading : UiState()
    object Empty: UiState()
    class Error(val throwable: Throwable) : UiState()
}