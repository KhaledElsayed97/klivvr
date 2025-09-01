package dev.khaled.klivvr.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.khaled.klivvr.data.repository.MainRepositoryImpl
import dev.khaled.klivvr.domain.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainRepository(
        @ApplicationContext context: Context
    ): MainRepository {
        return MainRepositoryImpl(context)
    }
}