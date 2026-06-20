package com.mjapa21.gamesapp.data.model

data class GameDetails(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val minimum_system_requirements: MinimumSystemRequirements
)

class MinimumSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)
