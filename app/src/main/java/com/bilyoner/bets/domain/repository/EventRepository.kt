package com.bilyoner.bets.domain.repository

import com.bilyoner.bets.core.Resource
import com.bilyoner.bets.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(): Flow<Resource<List<Event>>>
}