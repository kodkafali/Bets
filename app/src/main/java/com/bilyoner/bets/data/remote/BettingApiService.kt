package com.bilyoner.bets.data.remote

import com.bilyoner.bets.data.model.EventDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BettingApiService {
    @GET("/v4/sports/{leaguePath}/odds/")
    suspend fun getEvents(
        @Path("leaguePath") leaguePath: String,
        @Query("apiKey") apiKey: String,
        @Query("regions") regions: String
    ): Response<List<EventDto>>
}