package com.mjapa21.gamesapp.presentation.pages.gamelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.mjapa21.gamesapp.data.model.GamesListItem
import com.mjapa21.gamesapp.presentation.theme.AccentViolet
import com.mjapa21.gamesapp.presentation.theme.AppFont
import com.mjapa21.gamesapp.presentation.theme.SurfaceCard


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
                Text(text = state.message, fontFamily = AppFont)
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
            TopAppBar(title = { Text("Available Games", fontFamily = AppFont) })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(games.size) { index ->
                GameCard(game = games[index], onGameClick = onGameClick)
            }
        }
    }
}

@Composable
private fun GameCard(game: GamesListItem, onGameClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceCard)
            .clickable { onGameClick(game.id) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left violet accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(110.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(AccentViolet, AccentViolet.copy(alpha = 0f))
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                    )
            )

            // Text content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp, vertical = 14.dp)
            ) {
                Text(
                    text = game.title,
                    fontFamily = AppFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = game.short_description,
                    fontFamily = AppFont,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Thumbnail
            Box(
                modifier = Modifier
                    .size(width = 110.dp, height = 110.dp)
                    .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = game.thumbnail,
                    contentDescription = game.title,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(SurfaceCard, Color.Transparent),
                                startX = 0f,
                                endX = 60f
                            )
                        )
                )
            }
        }
    }
}
