package com.bilyoner.bets.data.repository

import com.bilyoner.bets.core.Resource
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T, R> safeApiCall(
        call: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Resource<R> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.Success(mapper(body))
                } else {
                    Resource.Error("Body is null")
                }
            } else {
                Resource.Error("HTTP ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}