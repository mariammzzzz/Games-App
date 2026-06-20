package com.mjapa21.gamesapp.presentation.pages.gamelist

import com.mjapa21.gamesapp.data.model.GamesListItem

sealed class GameListUiState {
    data object Loading : GameListUiState()
    data class Success(val games: List<GamesListItem>) : GameListUiState()
    data class Error(val message: String) : GameListUiState()
}
