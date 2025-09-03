package dev.khaled.klivvr.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(all = 16.dp)
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Loading may take some time, 201K cities :')",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(
                                Alignment.TopCenter
                            )
                            .padding(top = 32.dp)
                    )
                }
            }

            else -> {
                val isFocused = remember{mutableStateOf(false)}

                val scale by animateFloatAsState(
                    targetValue = if (isFocused.value) 1.05f else 1f,
                    animationSpec = tween(durationMillis = 200), label = ""
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = viewModel::updateSearchQuery,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                        .onFocusChanged { isFocused.value = it.isFocused }
                        .scale(scale),
                    placeholder = { Text("Search cities...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search, contentDescription = "Search"
                        )
                    },
                    singleLine = true
                )

                AnimatedVisibility(
                    visible = cities.isEmpty() && searchQuery.isNotBlank(),
                    enter = slideInHorizontally(
                        animationSpec = tween(500)
                    ),
                    exit = slideOutHorizontally(
                        animationSpec = tween(500)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "No cities found for \"$searchQuery\"",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                AnimatedVisibility(
                    visible = cities.isNotEmpty() || searchQuery.isBlank(), enter = expandHorizontally(
                        animationSpec = tween(300)
                    ), exit = shrinkHorizontally(
                        animationSpec = tween(300)
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ConnectingLine()

                        val groupedCities = remember(cities) {
                            cities.groupBy { it.name.first().uppercaseChar() }.toSortedMap()
                        }

                        LazyColumn {
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
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(externalPlayStoreUri)
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
}