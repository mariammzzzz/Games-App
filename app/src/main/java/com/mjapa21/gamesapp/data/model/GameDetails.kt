package com.mjapa21.gamesapp.data.model

data class GameDetails(
    val id: Int,
    val title: String,
    val description: String,
    val minimum_system_requirements: MinimumSystemRequirements?,
    //for example for the following game: "Elvenar"
    // https://www.freetogame.com/api/game?id=347
    // minimum_system_requirements field is not even returned in the response, so it should be nullable
    val screenshots: List<Screenshot>
)

data class MinimumSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)

data class Screenshot(
    val id: Int,
    val image: String
)