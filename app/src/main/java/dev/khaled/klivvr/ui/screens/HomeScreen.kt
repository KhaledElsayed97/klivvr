package dev.khaled.klivvr.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.khaled.klivvr.MainViewModel
import dev.khaled.klivvr.ui.components.CityItem
import dev.khaled.klivvr.ui.components.ConnectingLine
import dev.khaled.klivvr.ui.components.StickyHeader

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val cities by viewModel.cities.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Cities",
            modifier = Modifier.padding(16.dp)
        )
        
        when {
            cities.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ConnectingLine()
                    
                    LazyColumn {
                        cities.forEachIndexed { _, group ->
                            item {
                                StickyHeader(
                                    letter = group.letter
                                )
                            }

                            items(group.cities) { city ->
                                CityItem(city = city)
                            }
                        }
                    }
                }
            }
        }
    }
}