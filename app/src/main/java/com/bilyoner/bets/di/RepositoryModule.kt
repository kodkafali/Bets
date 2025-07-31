package com.bilyoner.bets.di

import com.bilyoner.bets.data.remote.BettingApiService
import com.bilyoner.bets.data.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEventRepository(api: BettingApiService): EventRepositoryImpl {
        return EventRepositoryImpl(api)
    }
}