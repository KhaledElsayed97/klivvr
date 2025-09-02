package dev.khaled.klivvr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.khaled.klivvr.ui.screens.HomeScreen
import dev.khaled.klivvr.ui.theme.KlivvrTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KlivvrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _->
                    HomeScreen()
                }
            }
        }
    }
}

