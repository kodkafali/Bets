package com.bilyoner.bets.presentation.betbasket

import com.bilyoner.bets.domain.model.Event
import com.bilyoner.bets.manager.AnalyticsManager
import com.bilyoner.bets.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BetBasketViewModel @Inject constructor(
    private val analyticsManager: AnalyticsManager
) : BaseViewModel() {

    fun getTotalOdds(data: List<Event>?): Double {
        return data.orEmpty()
            .map { it.selectedOdd }
            .fold(1.0) { acc, odd -> acc * odd }
    }
}