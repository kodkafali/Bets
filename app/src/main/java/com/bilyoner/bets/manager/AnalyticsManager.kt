package com.bilyoner.bets.manager

import android.content.Context
import android.os.Bundle
import com.bilyoner.bets.domain.model.Event
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AnalyticsManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val firebaseAnalytics = Firebase.analytics

    fun logMatchDetailEvent(matchId: String, matchName: String) {
        val bundle = Bundle().apply {
            putString("match_id", matchId)
            putString("match_name", matchName)
        }
        firebaseAnalytics.logEvent("match_detail_viewed", bundle)
    }

    fun logBetAdded(betItem: Event) {
        val bundle = Bundle().apply {
            putString("bet_id", betItem.id)
            putString("match_name", betItem.teams)
            putDouble("odd", betItem.selectedOdd)
        }
        firebaseAnalytics.logEvent("bet_added_to_cart", bundle)
    }

    fun logBetRemoved(betItem: Event) {
        val bundle = Bundle().apply {
            putString("bet_id", betItem.id)
            putString("match_name", betItem.teams)
        }
        firebaseAnalytics.logEvent("bet_removed_from_cart", bundle)
    }
}