package com.mjapa21.gamesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mjapa21.gamesapp.presentation.navigation.MainNavigationRoot
import com.mjapa21.gamesapp.ui.theme.GamesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamesAppTheme {
                MainNavigationRoot()
            }
        }
    }
}