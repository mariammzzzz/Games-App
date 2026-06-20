package com.mjapa21.gamesapp.presentation.pages.gamelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjapa21.gamesapp.data.RetrofitInstance
import kotlinx.coroutines.launch

class GameListViewModel : ViewModel() {
    var uiState by mutableStateOf<GameListUiState>(GameListUiState.Loading)
        private set

    init {
        fetchGames()
    }


    fun onTryAgainClick() {
        fetchGames()
    }

    fun fetchGames() {
        viewModelScope.launch {
            uiState = GameListUiState.Loading
            uiState = try {
                val gamesApi = RetrofitInstance.createGamesApi()
                val games = gamesApi.getGamesList()
                GameListUiState.Success(games)
            } catch (e: Exception) {
                GameListUiState.Error("Error occured. please try again")
            }
        }
    }
}