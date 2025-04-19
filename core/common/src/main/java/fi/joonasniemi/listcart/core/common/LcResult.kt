package fi.joonasniemi.listcart.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class LcResult <out T> {
    data object Loading : LcResult<Nothing>()
    data class Success<T>(val data: T) : LcResult<T>()
    data class Error(val exception: Throwable) : LcResult<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<LcResult<T>> = map<T, LcResult<T>> { LcResult.Success(it) }
    .onStart { emit(LcResult.Loading) }
    .catch { emit(LcResult.Error(it)) }