package com.mjapa21.gamesapp.presentation.pages.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mjapa21.gamesapp.data.RetrofitInstance
import com.mjapa21.gamesapp.data.model.GameDetails


@Composable
fun GameDetailsScreen(gameId: Int) {
    var details by remember { mutableStateOf<GameDetails?>(null) }

    LaunchedEffect(Unit) {
        val api = RetrofitInstance.createGamesApi()
        details = api.getGameDetails(gameId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {

        Text(modifier = Modifier.fillMaxWidth(), text = details?.description ?: "No details")
    }
}