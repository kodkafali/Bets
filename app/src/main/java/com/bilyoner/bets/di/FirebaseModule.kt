package com.bilyoner.bets.di

import android.content.Context
import com.bilyoner.bets.manager.AnalyticsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideAnalyticsManager(@ApplicationContext context: Context): AnalyticsManager {
        return AnalyticsManager(context)
    }
}