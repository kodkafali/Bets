package com.bilyoner.bets.domain.model

import com.bilyoner.bets.data.model.Outcome

data class Event(
    val id: String,
    val title: String,
    val teams: String,
    val outComes: List<Outcome>,
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    var selectedOdd: Double,
    var isSelected: Boolean = false
)