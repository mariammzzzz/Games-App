package com.mjapa21.gamesapp.presentation.pages.gamelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.mjapa21.gamesapp.data.model.GamesListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(onGameClick: (Int) -> Unit, viewModel: GameListViewModel = viewModel()) {
    when (val state = viewModel.uiState) {
        is GameListUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is GameListUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.message
                )
                Button(onClick = { viewModel.onTryAgainClick() }) {
                    Text("Try Again")
                }
            }
        }

        is GameListUiState.Success -> {
            GamesList(games = state.games, onGameClick = onGameClick)
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GamesList(games: List<GamesListItem>, onGameClick: (Int) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Available Games") }
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(games.size) {
                val game = games[it]

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onGameClick(game.id) }) {
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(8.dp)
                    ) {

                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { onGameClick(game.id) }
                                .padding(bottom = 16.dp),
                            text = game.title
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            text = game.short_description
                        )
                    }

                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        model = game.thumbnail,
                        contentDescription = game.title
                    )
                }
            }
        }
    }
}