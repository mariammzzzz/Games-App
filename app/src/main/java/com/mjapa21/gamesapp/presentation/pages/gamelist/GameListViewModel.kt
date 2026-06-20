package com.mjapa21.gamesapp.presentation.pages.gamelist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjapa21.gamesapp.data.RetrofitInstance
import com.mjapa21.gamesapp.data.mode.GamesListItem
import kotlinx.coroutines.launch

class GameListViewModel : ViewModel() {
    var games by mutableStateOf(emptyList<GamesListItem>())

    init {
        viewModelScope.launch {
            val gamesApi = RetrofitInstance.createGamesApi()
            games = gamesApi.getGamesList()
        }
    }
}