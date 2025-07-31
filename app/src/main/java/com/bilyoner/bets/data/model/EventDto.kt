package com.bilyoner.bets.data.model

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id") val id: String,
    @SerializedName("sport_key") val sportKey: String,
    @SerializedName("sport_title") val sportTitle: String,
    @SerializedName("commence_time") val commenceTime: String,
    @SerializedName("home_team") val homeTeam: String,
    @SerializedName("away_team") val awayTeam: String,
    @SerializedName("bookmakers") val bookmakers: List<Bookmaker>,
)

data class Bookmaker(
    @SerializedName("key") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("last_update") val lastUpdate: String,
    @SerializedName("markets") val markets: List<Market>,
)

data class Market(
    @SerializedName("key") val key: String,
    @SerializedName("last_update") val lastUpdate: String,
    @SerializedName("outcomes") val outcomes: List<Outcome>
)

data class Outcome(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
)