package dev.khaled.klivvr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.khaled.klivvr.MainViewModel
import dev.khaled.klivvr.ui.components.CityItem
import dev.khaled.klivvr.ui.components.ConnectingLine
import dev.khaled.klivvr.ui.components.StickyHeader

const val playStoreUri = "market://details?id=com.google.android.apps.maps"
const val externalPlayStoreUri =
    "https://play.google.com/store/apps/details?id=com.google.android.apps.maps"

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val cities by viewModel.cities.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(all = 16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::updateSearchQuery,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            placeholder = { Text("Search cities...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search"
                )
            },
            singleLine = true
        )

        when {
            cities.isEmpty() && searchQuery.isBlank() -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            cities.isEmpty() && searchQuery.isNotBlank() -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No cities found for \"$searchQuery\"", textAlign = TextAlign.Center
                    )
                }
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ConnectingLine()

                    LazyColumn {
                        val groupedCities =
                            cities.groupBy { it.name.first().uppercaseChar() }.toSortedMap()

                        groupedCities.forEach { (letter, cityList) ->
                            item {
                                StickyHeader(letter = letter)
                            }

                            items(cityList) { city ->
                                CityItem(city = city, onClick = {
                                    val mapsIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("geo:${city.lat},${city.lon}?q=${city.lat},${city.lon}(${city.name})")
                                    )

                                    if (mapsIntent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(mapsIntent)
                                    } else {
                                        val playStoreIntent = Intent(
                                            Intent.ACTION_VIEW, Uri.parse(playStoreUri)
                                        )
                                        if (playStoreIntent.resolveActivity(context.packageManager) != null) {
                                            context.startActivity(playStoreIntent)
                                        } else {
                                            val browserIntent = Intent(
                                                Intent.ACTION_VIEW, Uri.parse(externalPlayStoreUri)
                                            )
                                            context.startActivity(browserIntent)
                                        }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}