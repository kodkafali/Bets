package com.bilyoner.bets.data.repository

import com.bilyoner.bets.BuildConfig
import com.bilyoner.bets.core.Resource
import com.bilyoner.bets.data.remote.BettingApiService
import com.bilyoner.bets.domain.model.Event
import com.bilyoner.bets.utils.mapper.toDomain
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api: BettingApiService
) : BaseRepository() {

    suspend fun getEvents(league: String, regions: String): Resource<List<Event>> {
        return safeApiCall(
            call = { api.getEvents(league, BuildConfig.API_KEY, regions) },
            mapper = { dtoList -> dtoList.map { it.toDomain() } }
        )
    }
}