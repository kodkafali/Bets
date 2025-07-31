package com.bilyoner.bets.domain.usecase

import com.bilyoner.bets.core.Resource
import com.bilyoner.bets.data.repository.EventRepositoryImpl
import com.bilyoner.bets.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventRepositoryImpl
) {
    operator fun invoke(league : String, regions: String): Flow<Resource<List<Event>>> = flow{
         emit(repository.getEvents(league, regions))
    }
}