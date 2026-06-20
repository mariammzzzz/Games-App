package com.mjapa21.gamesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.mjapa21.gamesapp.presentation.pages.details.GameDetailsScreen
import com.mjapa21.gamesapp.presentation.pages.gamelist.GameListScreen


@Composable
fun MainNavigationRoot() {
    val backStack = rememberNavBackStack(Destinations.GamesList)
    NavDisplay(backStack = backStack) { navKey ->
        when (navKey) {
            is Destinations.GamesList -> NavEntry(navKey) {
                GameListScreen(onGameClick = { gameId ->
                    backStack.add(
                        Destinations.GameDetails(gameId = gameId)
                    )
                })
            }

            is Destinations.GameDetails -> NavEntry(navKey) { GameDetailsScreen() } //TODO PASS THE ID LATER
            else -> throw IllegalStateException("Unknown destination: $navKey")
        }
    }

}