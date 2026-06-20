package com.mjapa21.gamesapp.presentation.pages.details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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

    details?.let { game ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // ── Title ──────────────────────────────────────────────────
            Text(
                text = game.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            // ── Screenshot carousel ────────────────────────────────────
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(game.screenshots) { screenshot ->
                    AsyncImage(
                        model = screenshot.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 280.dp, height = 160.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }

            // ── Description ────────────────────────────────────────────
            Text(
                text = game.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )

            // ── System requirements ────────────────────────────────────
            Text(
                text = "Minimum System Requirements",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    with(game.minimum_system_requirements) {
                        RequirementRow("OS", os)
                        RequirementRow("Processor", processor)
                        RequirementRow("Memory", memory)
                        RequirementRow("Graphics", graphics)
                        RequirementRow("Storage", storage)
                    }
                }
            }
        }
    }
}


@Composable
private fun RequirementRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(2f)
        )
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}