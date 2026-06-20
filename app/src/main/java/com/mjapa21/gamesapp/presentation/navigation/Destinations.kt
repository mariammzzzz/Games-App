package com.mjapa21.gamesapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface Destinations : NavKey {
    @Serializable
    data object GamesList : Destinations

    @Serializable
    data class GameDetails(val gameId: Int) : Destinations
}