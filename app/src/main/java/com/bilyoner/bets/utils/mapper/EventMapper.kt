package com.bilyoner.bets.utils.mapper

import com.bilyoner.bets.data.model.EventDto
import com.bilyoner.bets.domain.model.Event

fun EventDto.toDomain(): Event {
    return Event(
        id = this.id,
        teams = "${this.homeTeam} vs ${this.awayTeam}",
        title = this.sportTitle,
        homeTeam = this.homeTeam,
        awayTeam = this.awayTeam,
        date = this.commenceTime,
        outComes = this.bookmakers.first().markets.first().outcomes,
        selectedOdd = 0.0
    )
}