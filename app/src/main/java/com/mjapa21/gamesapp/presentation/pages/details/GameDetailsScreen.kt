package com.mjapa21.gamesapp.presentation.pages.details

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import com.mjapa21.gamesapp.data.RetrofitInstance
import com.mjapa21.gamesapp.data.model.GameDetails


@Composable
fun GameDetailsScreen(gameId: Int) {
    var details by remember { mutableStateOf<GameDetails?>(null) }
    val context = LocalContext.current

    //TODO how would we pass the gameId to the viewmodel and have a proper implementation
    LaunchedEffect(Unit) {
        try {
            val api = RetrofitInstance.createGamesApi()
            details = api.getGameDetails(gameId)
        } catch (_: Exception) {
            Toast.makeText(context, "Failed to load details", Toast.LENGTH_SHORT).show()
        }
    }

    details?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {

            Text(modifier = Modifier.fillMaxWidth(), text = it.description)
        }
    }
}