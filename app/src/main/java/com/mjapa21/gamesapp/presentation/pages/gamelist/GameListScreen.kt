package com.mjapa21.gamesapp.presentation.pages.gamelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(onGameClick: (Int) -> Unit, viewModel: GameListViewModel = viewModel()) {
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
            items(viewModel.games.size) {
                val game = viewModel.games[it]

                Row(modifier = Modifier.fillMaxWidth()) {
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