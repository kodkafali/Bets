package com.bilyoner.bets.presentation.betbulletin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bilyoner.bets.core.Resource
import com.bilyoner.bets.domain.model.Event
import com.bilyoner.bets.domain.usecase.GetEventsUseCase
import com.bilyoner.bets.manager.AnalyticsManager
import com.bilyoner.bets.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.map
import com.bilyoner.bets.enums.League

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCase: GetEventsUseCase,
    private val analyticsManager: AnalyticsManager
) : BaseViewModel() {

    private val _eventsState = MutableStateFlow<Resource<List<Event>>>(Resource.Loading)
    val eventsState: StateFlow<Resource<List<Event>>> = _eventsState

    private val _filteredEvents = MutableStateFlow<List<Event>>(emptyList())
    val filteredEvents: StateFlow<List<Event>> = _filteredEvents

    val betBasket = MutableLiveData<List<Event>>(emptyList())
    val basketCount: LiveData<Int> = betBasket.map { it.size }

    private var allEvents: List<Event> = emptyList()

    private val _selectedLeague = MutableStateFlow(League.SUPER_LIG)
    val selectedLeague: StateFlow<League> = _selectedLeague

    init {
        getEvents(_selectedLeague.value.id, "us")
    }

    private fun getEvents(league: String, regions: String) {
        viewModelScope.launch {
            eventUseCase(league, regions).collect { result ->
                _eventsState.value = result
                if (result is Resource.Success) {
                    allEvents = result.data.orEmpty()
                    _filteredEvents.value = allEvents // İlk durumda tamamı gösterilsin
                }
            }
        }
    }

    fun selectLeague(league: League) {
        _selectedLeague.value = league
        getEvents(league.id, "us")
    }

    fun updateSearchQuery(query: String) {
        _filteredEvents.value = if (query.isBlank()) {
            allEvents
        } else {
            allEvents.filter {
                it.teams.contains(query, ignoreCase = true) ||
                        it.title.contains(query, ignoreCase = true)
            }
        }
    }

    fun addToBasket(event: Event) {
        val current = betBasket.value.orEmpty()
        if (current.none { it.id == event.id }) {
            betBasket.value = current + event
            analyticsManager.logBetAdded(event)
        }
    }

    fun removeFromBasket(event: Event) {
        betBasket.value = betBasket.value.orEmpty().filterNot { it.id == event.id }
        analyticsManager.logBetRemoved(event)
    }
}