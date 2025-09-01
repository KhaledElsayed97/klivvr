package dev.khaled.klivvr.data.repository

import android.content.Context
import androidx.annotation.RawRes
import dev.khaled.klivvr.data.model.City
import dev.khaled.klivvr.domain.model.CityDomainModel
import dev.khaled.klivvr.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(private val context: Context) : MainRepository {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getCities(@RawRes resId: Int): List<CityDomainModel> =
        withContext(Dispatchers.IO) {
            val inputStream = context.resources.openRawResource(resId)
            val text = inputStream.bufferedReader().use { it.readText() }
            val cityList = json.decodeFromString<List<City>>(text)

            cityList.map {
                CityDomainModel(
                    id = it.id,
                    name = it.name,
                    country = it.country,
                    lat = it.coordinates.lat,
                    lon = it.coordinates.lon
                )
            }
        }
}