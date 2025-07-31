package com.bilyoner.bets.di

import com.bilyoner.bets.data.repository.EventRepositoryImpl
import com.bilyoner.bets.domain.usecase.GetEventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetEventsUseCase(repository: EventRepositoryImpl): GetEventsUseCase {
        return GetEventsUseCase(repository)
    }
}