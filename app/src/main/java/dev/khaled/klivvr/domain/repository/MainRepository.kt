package dev.khaled.klivvr.domain.repository

import androidx.annotation.RawRes
import dev.khaled.klivvr.domain.model.CityDomainModel

interface MainRepository {
    suspend fun getCities(@RawRes resId: Int):List<CityDomainModel>
}
